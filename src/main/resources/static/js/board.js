let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		
		});
		$("#btn-update").on("click", () => {
			this.update();
		
		});
		
	}

	, save: function() {
		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
			category: $('#category').val()
		};
		//console.log(data);
		
		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data:JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
			
		}).done(function(response){
			console.log(response);
			alert('글쓰기가 완료되었습니다.');
			location.href="/";
			
		}).fail(function(error){
			
			
			alert(JSON.stringify(error));

			
		});
		
	}, deleteById: function() {
		let id = $('#id').text();
		console.log(id);
		$.ajax({
			//회원가입 수행 요청
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
			
		}).done(function(response){
			console.log(response);
			alert('삭제가 완료되었습니다.');
			location.href="/";
			
		}).fail(function(error){
			
			
			alert(JSON.stringify(error));

			
		});
		
	}
	, update: function() {
		let data = {
			id: $('#id').val(),
			title: $('#title').val(),
			content: $('#content').val(),
			category: $('#category').val()
		};
		//console.log(data);
		
		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: "/api/board",
			data:JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
			
		}).done(function(response){
			console.log(response);
			alert('글수정가 완료되었습니다.');
			location.href="/board/"+data.id;
			
		}).fail(function(error){
			
			
			alert(JSON.stringify(error));

			
		});
		
	}

}



index.init();