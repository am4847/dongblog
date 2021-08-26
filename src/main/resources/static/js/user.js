let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
			$("#btn-update").on("click", () => {
			this.update();
		});
	}

	, save: function() {
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		//console.log(data);
		
		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data:JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
			
		}).done(function(response){
			console.log(response);
			alert('회원가입이 완료되었습니다.');
			location.href="/";
			
		}).fail(function(error){
			
			
			alert(JSON.stringify(error));

			
		});
		
	}, update: function() {
		let data = {
			id: $('#id').val(),
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: "/user",
			data:JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
			
		}).done(function(response){
			console.log(response);
			alert('회원수정이 완료되었습니다.');
			location.href="/";
			
		}).fail(function(error){
			
			
			alert(JSON.stringify(error));

			
		});
		
	}
	/*, login: function() {
		let data = {
			username: $('#username').val(),
			password: $('#password').val()
		};
		//console.log(data);
		
		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/user/login",
			data:JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
			
		}).done(function(response){
			console.log(response);
			alert('로그인이 완료되었습니다.');
			location.href="/";
			
		}).fail(function(error){
			
			
			alert(JSON.stringify(error));

			
		});
		
	}*/
}



index.init();