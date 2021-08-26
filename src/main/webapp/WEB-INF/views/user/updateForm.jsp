<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 700px; ">
	<form>
		<div class="form-group">
			<label for="username">Username :</label> 
			<input type="text" class="form-control" placeholder="Enter Username" id="username"  value="${principal.user.username }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter Password" id="password"  >
		</div>
		<div class="form-group">
			<label for="email">Email :</label> 
			<input type="email" class="form-control" placeholder="Enter Email" id="email" value="${principal.user.email }" >
		</div>
	


	</form>
	<div style="display: flex; justify-content: space-between;">
	<input type="hidden" id="id" value="${principal.user.id }">
		<button class="btn btn-outline-dark"  onclick="javascript:btn_history()">돌아가기</button>

			<button id="btn-update" class="btn btn-outline-dark">회원수정완료</button>
	</div>

</div>
<script src="/js/user.js"></script>
<script type="text/javascript">
function btn_history(){
	if(confirm('사이트에서 나가시겠습니까?\n변경사항이 저장되지 않을 수 있습니다.')==true)location.href='/user/detail';  
		}
</script>
<%@ include file="../layout/footer.jsp"%>