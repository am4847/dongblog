<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 500px;">

	<form>
		<div class="form-group">
			<div class="label-div">
				<label for="userId">ID &nbsp; </label> <label id="userIdComment"></label>
			</div>
			<input type="text" class="form-control" placeholder="Enter userId" id="userId">
		</div>

		<div class="form-group">
			<div class="label-div">
				<label for="userName">Nickname &nbsp; </label> <label id="userNameComment"></label>
			</div>
			<input type="text" class="form-control" placeholder="Enter Nickname" id="userName">
		</div>
		<div class="form-group">
			<div class="label-div">
				<label for="password">Password </label> <label id="passwordComment"></label>
			</div>
			<input type="password" class="form-control" placeholder="Enter Password" id="password">
		</div>

		<div class="form-group">
			<div class="label-div">
				<label for="passwordChk">Password Check &nbsp; </label> <label id="passwordChkComment"></label>
			</div>
			<input type="password" class="form-control" placeholder="Enter RE Password" id="passwordChk" disabled="disabled">

		</div>

		<div class="form-group">
			<div class="label-div">
				<label for="email">Email &nbsp;</label> <label id="emailComment"></label>
			</div>
			<input type="email" class="form-control" placeholder="Enter Email" id="email">

		</div>



	</form>
	<div style="display: flex; justify-content: space-between;">
		<button class="btn btn-outline-dark" onclick="location.href='/'">돌아가기</button>
			<input type="hidden" id ="buttonType"  value="#btn-save" >
		<button id="btn-save" class="btn btn-outline-danger" disabled="disabled">회원가입</button>
	</div>

</div>
<script src="/js/user.js"></script>
<link rel="stylesheet" type="text/css" href="/css/user.css">
<%@ include file="../layout/footer.jsp"%>