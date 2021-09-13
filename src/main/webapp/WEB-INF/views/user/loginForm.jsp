
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 500px;">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">ID </label> <input name="username" type="text" class="form-control" placeholder="Enter userId" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password </label> <input name="password" type="password" class="form-control" placeholder="Enter Password" id="password">
		</div>
		<div style="display: flex; justify-content: space-between;">
			<button class="btn btn-outline-dark" onclick="location.href='/'">돌아가기</button>

			<div>

				<button id="btn-login" class="btn btn-outline-dark">로그인</button>
			</div>
		</div>
		<hr />
		<div class="card mb-2">
			<div class="card-header bg-light">
				<i class="fa fa-comment fa"></i> 소셜로그인
			</div>
			<div class="card-body ">
				<div class="d-flex justify-content-between ">
					<a href="/oauth2/authorization/google"> <img height="40"  width="135"   src="/image/google_login_button.png"></a>
					<a href="/oauth2/authorization/kakao"> <img height="40"  width="135" src="/image/kakao_login_button.png"></a>
					<a href="/oauth2/authorization/naver"> <img height="40"  width="135" src="/image/naver_login_button.png"></a>
				</div>
			


			</div>
		</div>
	</form>

</div>
<script src="/js/user.js"></script>
<link rel="stylesheet" type="text/css" href="/css/user.css">
<%@ include file="../layout/footer.jsp"%>