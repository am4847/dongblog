<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 500px;">
	<form>
		<div class="form-group">
			<div class="label-div">
				<label for="userId">ID &nbsp; </label> <label id="userIdComment"></label>
			</div>
			<input type="text" class="form-control" placeholder="Enter UserId" id="userId" value="${principal.user.userId }" readonly="readonly">
		</div>
		<div class="form-group">
			<div class="label-div">
				<label for="userName">Nickname &nbsp; </label> <label id="userNameComment"></label>
			</div>
			<input type="text" class="form-control" placeholder="Enter Nickname" id="userName" value="${principal.user.userName }">
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
			<input type="password" class="form-control" placeholder="Enter RE Password" id="passwordChk">
		</div>
		<div class="form-group">
			<div class="label-div">
				<label for="email">Email &nbsp;</label> <label id="emailComment"></label>
			</div>
			<input type="email" class="form-control" placeholder="Enter Email" id="email" value="${principal.user.email }">
		</div>



	</form>
	<div style="display: flex; justify-content: space-between;">
		<input type="hidden" id="no" value="${principal.user.no }">
		<button class="btn btn-outline-dark" onclick="javascript:btn_history()">돌아가기</button>
		
		<input type="hidden" id ="buttonType"  value="#btn-update">
		<button id="btn-update" class="btn btn-outline-danger" disabled="disabled">회원수정완료</button>
	</div>

</div>
<script src="/js/user.js"></script>
<link rel="stylesheet" type="text/css" href="/css/user.css">
<script type="text/javascript">
	function btn_history() {
		if (confirm('사이트에서 나가시겠습니까?\n변경사항이 저장되지 않을 수 있습니다.') == true)
			location.href = '/user/detail';
	}
</script>
<%@ include file="../layout/footer.jsp"%>