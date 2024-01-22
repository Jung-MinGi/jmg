package com.blog.jmg.Controller.api;

import com.amazonaws.services.s3.AmazonS3Client;
import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.domain.s3Domain.S3FileProcess;
import com.blog.jmg.domain.s3Domain.TempImg;
import com.blog.jmg.domain.service.TextDataAccessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class TableDataGetApiController {
    private final S3FileProcess s3FileProcess;
    private final TextDataAccessService service;
    private final ObjectMapper objectMapper;

    private static final String regex = "^[\\p{L}0-9\\s]+$";
    public static final Pattern pattern = Pattern.compile(regex);

    private final AmazonS3Client client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    @Value("${cloud.aws.s3.upload-tempPath}")
    private String tempPath;
    @Value("${cloud.aws.s3.upload-Path}")
    private String path;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandle(Exception e){
        return new ResponseEntity<>(Arrays.toString(e.getStackTrace()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/tables")
    public ResponseEntity<List<String>> getAllTablesName() {
        List<String> tables = service.getTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }
    @PostMapping("/delete/{category}/{title}")
    public ResponseEntity<String> deleteText(@PathVariable String category
    ,@PathVariable String title) throws IOException {
        log.info("deleteText={} {}",category,title);
//        Map<String, String> map = objectMapper.readValue(id, new TypeReference<>() {
//        });
        FindTextParamDTO dto = new FindTextParamDTO();
        dto.setCategory(category);
        dto.setTitle(title);
        service.deleteText(dto);
        return new ResponseEntity<>("글 삭제 완료", HttpStatus.OK);
    }

    //⬇️s3에 이미지 임시 경로에 업로드하는 로직(재사용로직)
    @PostMapping("/temp/image")
    @ResponseBody
    public TempImg TmpimageSaveToS3(MultipartFile file) throws IOException {

            return s3FileProcess.tempImageFileUploadToS3(file);
    }

    @PostMapping("/image") // summernote에 작성된 글 완성본 넘어올때 처리하는 핸들러 비동기로 넘어온다
    public ResponseEntity<Object> summerAll(@RequestBody String contents) throws JsonProcessingException {
            //⬇️JSON으로 넘어온 글번호,글제목,글내용 map으로 파싱후(재사용로직)
            Map<String, String> map = objectMapper.readValue(contents, new TypeReference<>() {
            });
            log.info(map.toString());


            //⬇️검증 로직 수행(재사용로직) 💥💥추후에 인터셉터로 옮김
            for (String s : map.keySet()) {
                if(!pattern.matcher(map.get("title")).matches()) return new ResponseEntity<>("제목에 특수문자 사용 금지",HttpStatus.BAD_REQUEST);
                if (map.get(s) == null || map.get(s).isEmpty()) {
                    return new ResponseEntity<>("모든 칸을 입력하세요, ", HttpStatus.BAD_REQUEST);
                }
            }

            //⬇️넘어온 content안에서 img태그 안에 src값만 꺼낸 후 list에 담음,골라낸 src로 s3에 접근해서 이미지 파일을 가져온 후 경로 수정후 재저장(재사용로직)
            HashSet<String> set = s3FileProcess.imgTagFindSrc(map.get("content"));
            //
            //⬇️실제 db에 들어갈 src값을 경로 수정한 값으로 바꿈(재사용로직)
            contents = s3FileProcess.replaceTempPathToOriginalPath(map.get("content"));
            //

            //⬇️s3 temp안에 내용 모두 삭제 로직(재사용로직)
            s3FileProcess.deleteTempFolder();

            FindTextParamDTO dto = new FindTextParamDTO();
            dto.setCategory(map.get("originalCategoryName"));
            dto.setTitle(map.get("title"));

            //⬇️수정시에는 기존에 있던 DB에서 src값 뺴와서 새로 들어온 src값과 비교후 존재하지 않는다면 s3에 접근해서 삭제해야됨
            if (map.size() == 5) {

                WriteForm form = service.findText(dto);
                Element body = Jsoup.parse(form.getContent()).body();
                Elements img = body.getElementsByTag("img");
                for (Element element : img) {
                    String src = element.attr("src");
                    if (!set.contains(src.substring(src.lastIndexOf("image"))) && src.contains(bucketName)) {//위키피디아 이런건 그냥 무시해야됨
                        String imgKey = src.substring(src.lastIndexOf("image"));
                        client.deleteObject(bucketName, imgKey);
                    }
                }
            }


            //⬇️이제 db로 저장!!(재사용로직)
            WriteForm writeForm = new WriteForm();
            writeForm.setCategory(map.get("tableName"));
            writeForm.setTitle(map.get("title").trim());
            writeForm.setContent(contents);
            if (map.size() == 5) {
                log.info("update logic");
                writeForm.setId(Integer.parseInt(map.get("id")));
                if(!map.get("originalCategoryName").equals(map.get("tableName"))){//category바뀐 경우
                    service.deleteText(dto);
                    service.save(writeForm);
                }else{//안바뀐 경우
                    service.update(writeForm);
                }
            } else {
                service.save(writeForm);
            }
            return new ResponseEntity<>(writeForm, HttpStatus.OK);
    }
}
