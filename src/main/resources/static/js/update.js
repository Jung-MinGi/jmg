let updateIndex = {

		init: function(){
//            이벤트핸들러 나열할 때 "," 안씀
			$("#btn-save").on("click", ()=>{
				this.save();
			});


		},

		save: function(){
			let data = {
			        id: $("#id").val(),
			        originalCategoryName:$("#originalCategoryName").val(),
			        tableName:$("#tableNameSelect").val(),
			        title: $("#title").val(),
					content: $("#summernote").val()
			};
            alert(JSON.stringify(data))
			$.ajax({
				type: "POST",
				url: "/api/image",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
//				dataType: "json"
			}).done(function(resp){
				alert(" 완료되었습니다.");
				location.href = `/writeShow/${resp.category}/${resp.title}`;
			}).fail(function(error){
				console.log(JSON.stringify(error));
			});
		}
}

updateIndex.init();
