let checkId = 0;
let checkName = 0;
let checkPwd = 0;
let checkPwdChk = 0;
let checkEmail = 0;
let buttonType = $("#buttonType").val();
if(buttonType=="#btn-update"){
	checkId=1;
	checkName = 1;
	 checkEmail = 1;
}
let index = {

	init: function() {
		
		
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#userId").on("input", () => {
			if(buttonType=="#btn-save" || buttonType=="#btn-update")this.checkIdDB();
		});
		$("#userName").on("input", () => {
			if(buttonType=="#btn-save" || buttonType=="#btn-update")this.checkNameDB();
		});
		
		$("#password").on("input", () => {
			if(buttonType=="#btn-save" || buttonType=="#btn-update")this.checkPassword();
		});
		$("#passwordChk").on("input", () => {
			if(buttonType=="#btn-save" || buttonType=="#btn-update")this.checkPasswordChk();
		});
			$("#email").on("input", () => {
			if(buttonType=="#btn-save" || buttonType=="#btn-update")this.checkEmail();
		});
	}

	, save: function() {
		let data = {
			userId: $('#userId').val(),
			userName: $('#userName').val(),
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
			no: $('#no').val(),
			userId: $('#userId').val(),
			userName: $('#userName').val(),
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
		
	}, checkIdDB: function() {
			
			let id = $('#userId').val();
			var regExp = /^[0-9a-z]{4,11}$/;
		 	 if( !regExp.test(id) ) {
			$("#userId").css("background-color", "#FFCECE");
			$("#userIdComment").text("4~11사이 소문자 영문/숫자 사용");
			$("#userIdComment").css("color", "#ff4242");
         	checkId=0;
           
       		 }else{
					
				let data = { 
				userId : id
				}
						
				$.ajax({
					type: "POST",
					url: "/check/userId",
					data:JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
					contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
					dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
				}).done(function(response){
					
				console.log("response :"+response);
			
					if(response==0){
						checkId=0;
						$("#userId").css("background-color", "#FFCECE");
						$("#userIdComment").text("존재하는 아이디입니다.");
						$("#userIdComment").css("color", "#ff4242");
					}else if(response==1 ){
						
						checkId=1;
						console.log("checkId: "+checkId);
						$("#userId").css("background-color", "#B0F6AC");
						$("#userIdComment").text("");
					}
					checkButtonIsable();
					
				}).fail(function(error){
					
					
					alert(JSON.stringify(error));
		
					
				});
				}
			checkButtonIsable();
		}, checkNameDB: function() {
			console.log(buttonType+" "+checkId);
			let userName = $('#userName').val();
			var regExp  = /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{2,10}$/;
		 	 if( !regExp.test(userName) ) {
			$("#userName").css("background-color", "#FFCECE");
			$("#userNameComment").text("3-10 특수문자 제외");
			$("#userNameComment").css("color", "#ff4242");
         	checkName=0;
           
       		 }else{
					
				let data = { 
				userName : userName
				}
						
				$.ajax({
					type: "POST",
					url: "/check/userName",
					data:JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
					contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
					dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
				}).done(function(response){
					
					console.log("response :"+response);
			
					if(response==0){
						checkName=0;
						$("#userName").css("background-color", "#FFCECE");
						$("#userNameComment").text("존재하는 별명입니다.");
						$("#userNameComment").css("color", "#ff4242");
					}else if(response==1 ){
						checkName=1;
						$("#userName").css("background-color", "#B0F6AC");
						$("#userNameComment").text("");
					}
						
					checkButtonIsable();
					
				}).fail(function(error){
					
					
					alert(JSON.stringify(error));
		
					
				});
				}
				console.log(buttonType);
				checkButtonIsable();
		}, checkPassword: function() {
	
			let password = $('#password').val();
			let passwordChk = $('#passwordChk').val();
			var regExp = /^(?=.*[a-zA-Z])[0-9a-zA-Z@$!%*#?&]{4,15}$/;
		 	 if( !regExp.test(password)) {
				checkPwd=0;
				$("#password").css("background-color", "#FFCECE");
				$("#passwordComment").text("소문자 포함된 비밀번호를 입력하세요(특수문자가능) ");
				$("#passwordComment").css("color", "#ff4242");
	         	 
           
       		 }else{
				checkPwd=1;
				$("#passwordChk").prop("disabled", false);
				$("#password").css("background-color", "#B0F6AC");
				$("#passwordComment").text("");
			
				}
				
				
				
			if(passwordChk!=""){	
			 if(password==passwordChk) {
				checkPwdChk=1;
				$("#passwordChk").css("background-color", "#B0F6AC");
				$("#passwordChkComment").text("");
       		 }else{
				checkPwdChk=0;
				$("#passwordChk").css("background-color", "#FFCECE");
				$("#passwordChkComment").text("비밀번호가 일치하지 않습니다.");
				$("#passwordChkComment").css("color", "#ff4242");
				}
			}
			checkButtonIsable();
			
				
				
		},checkPasswordChk:function() {
	
			let password = $('#password').val();
			let passwordChk = $('#passwordChk').val();
			console.log(password+"\t"+passwordChk);
			console.log(password==passwordChk);
		 	 if(password==passwordChk) {
				checkPwdChk=1;
				$("#passwordChk").css("background-color", "#B0F6AC");
				$("#passwordChkComment").text("");
       		 }else{
				checkPwdChk=0;
				$("#passwordChk").css("background-color", "#FFCECE");
				$("#passwordChkComment").text("비밀번호가 일치하지 않습니다.");
				$("#passwordChkComment").css("color", "#ff4242");
				}
				
				
				
				checkButtonIsable();
				
		}, checkEmail: function() {
	
			let email = $('#email').val();
	
			var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		 	 if( !regExp.test(email)) {
				checkEmail=0;
				$("#email").css("background-color", "#FFCECE");
				$("#emailComment").text("@ . 이외의 이메일을 정확히 적어주세요 ");
				$("#emailComment").css("color", "#ff4242");
	         	 
           
       		 }else{
				checkEmail=1;
				
				$("#email").css("background-color", "#B0F6AC");
				$("#emailComment").text("");
			
				}
				
				
				
				
				
			checkButtonIsable();
			
				
				
		}
	
}

function checkButtonIsable(){
	
	if(checkId==1 &&checkName==1 &&checkPwd == 1 &&checkPwdChk == 1 &&checkEmail == 1){
			$(buttonType).attr('class','btn btn-outline-success');
			$(buttonType).prop("disabled", false);
		}else{
			$(buttonType).attr('class','btn btn-outline-danger');
			$(buttonType).prop("disabled", true);
		}

}
index.init();

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