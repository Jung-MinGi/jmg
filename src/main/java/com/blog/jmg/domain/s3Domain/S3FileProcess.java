package com.blog.jmg.domain.s3Domain;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3FileProcess {
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
    public TempImg tempImageFileUploadToS3(MultipartFile file) throws IOException {
        String serverFileName = getServerFileName(file.getOriginalFilename());
        String s3Path = tempPath + serverFileName;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        saveImageFileToS3(new PutObjectRequest(bucketName, s3Path, file.getInputStream(), objectMetadata));
        return new TempImg(client.getUrl(bucketName, s3Path).toString(), file.getOriginalFilename());
    }
    public HashSet<String> imgTagFindSrc(String bodyContent){
        Element body = Jsoup.parse(bodyContent).body();
        Elements imgTag = body.getElementsByTag("img");
        ArrayList<String> list = new ArrayList<>();
        for (Element element : imgTag) {
            String src = element.attr("src");
            try{
                list.add(src.substring(src.lastIndexOf("image")));
            }catch (StringIndexOutOfBoundsException e){
                log.info("작성자가 올린 이미지가 아닙니다");
            }
        }
        HashSet<String> set = new HashSet<>();
        for (String s : list) {
            String dataFolder = LocalDate.now() + "/";
            S3Object o = client.getObject(bucketName, s);
            String fileName = s.substring(s.lastIndexOf("/") + 1);
            String realPath = path + dataFolder + fileName;
            saveImageFileToS3(new PutObjectRequest(bucketName, realPath, o.getObjectContent(), o.getObjectMetadata()));
            set.add(s);
        }

        return set;
    }
    public String replaceTempPathToOriginalPath(String bodyContent) {
        Element body = Jsoup.parse(bodyContent).body();
        Elements imgTag = body.getElementsByTag("img");
        for (Element element : imgTag) {
            String src = element.attr("src");
            log.info("Before@@@@@@@@@@@@@@@@@@@@@@@@={}", src);
            src = src.replaceFirst("temp", LocalDate.now().toString());
            element.attr("src", src);
            log.info("After@@@@@@@@@@@@@@@@@@@@@@@@={}", element.attr("src"));
        }
        return body.html();
    }
//    public void deleteTempFolder() {
//
//        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName, tempPath, null, null, null);
//        ObjectListing objectListing = client.listObjects(listObjectsRequest);
//        if(objectListing!=null){
//            List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
//            for (S3ObjectSummary objectSummary : objectSummaries) {
//                log.info("key = {}", objectSummary.getKey());
//                client.deleteObject(bucketName, objectSummary.getKey());
//            }
//        }
//    }

    public void deleteTempFolder() {
        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName, tempPath, null, null, null);
            ObjectListing objectListing = client.listObjects(listObjectsRequest);

            if (objectListing != null) {
                List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

                for (S3ObjectSummary objectSummary : objectSummaries) {
                    log.info("Deleting key = {}", objectSummary.getKey());
                    client.deleteObject(bucketName, objectSummary.getKey());
                }
            }
        } catch (AmazonS3Exception e) {
            log.error("Error while listing or deleting objects: {}", e.getMessage());
            // 예외 처리 로직 추가

        }
    }

    private void saveImageFileToS3(PutObjectRequest putObjectRequest) {
        client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private static String getServerFileName(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(pos + 1);
        String string = UUID.randomUUID().toString();
        String n = string + "." + substring;
        System.out.println("n = " + n);
        return n;
    }
}
