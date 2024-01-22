let index = {

		init: function(){
//            이벤트핸들러 나열할 때 "," 안씀
			$("#btn-save").on("click", ()=>{
				this.save();
			});


		},

		save: function(){

			let data = {
			        tableName:$("#tableNameSelect").val(),
			        title: $("#title").val(),
					content: $("#summernote").val()
			};

            alert(data.tableName);
			$.ajax({
				type: "POST",
				url: "/api/image",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
			}).done(function(resp){
				alert("글 작성 완료되었습니다."+JSON.stringify(resp));
				location.href = `/writeShow/${resp.category}/${resp.title}`;
			}).fail(function(error){
				alert(error.responseText);
			});
		}
}

index.init();
