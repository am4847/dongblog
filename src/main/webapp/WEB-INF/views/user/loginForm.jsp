
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 700px;">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username :</label> <input name="username" type="text" class="form-control" placeholder="Enter Username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input name="password" type="password" class="form-control" placeholder="Enter Password" id="password">
		</div>
		<div style="display: flex; justify-content: space-between;">
			<button class="btn btn-outline-dark" onclick="location.href='/'">돌아가기</button>

			<div>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=a51945892037291a79698727ae1fad25&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"> 
				<img height="40" src="/image/kakao_login_button.png"></a>
				<button id="btn-login" class="btn btn-outline-dark">로그인</button>
			</div>
		</div>

	</form>

</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>