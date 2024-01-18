const PAGE={
    paging : function(response,category,totalPageCount,pageNo,totalElementCount,fn){

        if(totalElementCount==0){
            document.querySelector('#pagingArea-'+category).innerHTML = "";
            return false;
        }
        let pageBlock =10;//한 페이지에 나오는 블록의 개수
        let blockNo = PAGE.toInt(pageNo/pageBlock)+1;//블록 넘버
        let startBlockNumInCurrentPage = (blockNo-1)*pageBlock+1;//시작페이지 넘버
        let endBlockNumInCurrentPage = blockNo*pageBlock-1;//끝 페이지 넘버

        if(endBlockNumInCurrentPage > totalPageCount-1){
            endBlockNumInCurrentPage = totalPageCount;
        }

        let prevBlockPageNo  = (blockNo-1)*pageBlock-1;
        let nextBlockPageNo  = blockNo*pageBlock;

        let strHTML="<p>";

        if(response.hasPreviousPage){
            strHTML+="<li><a href='javascript:"+fn+"(\""+category+"\","+(pageNo-1)+");'><span>prev</span></a></li>"
        }

        for(let i = startBlockNumInCurrentPage; i<=endBlockNumInCurrentPage; i++){
            if(i==pageNo){
                strHTML+= "<li class='active'><a href='javascript:"+fn+"(\""+category+"\","+i+");'>"+i+"</a></li>";
            }else{
            strHTML += "<li><a href='javascript:"+fn+"(\""+category+"\","+i+");'>"+i+"</a></li>";
            }
        }
        if(response.hasNextPage){
            strHTML+="<li><a href='javascript:"+fn+"(\""+category+"\","+(pageNo+1)+");'><span>next</span></a></li>"
        }
        strHTML+="</p>";
        let element = document.querySelector('#pagingArea-'+category);
        element.innerHTML = strHTML;


    },

     toInt: function(value) {
     		if (value != null) {
     			return parseInt(value, 10);
     		}
     	}
}