<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 700px;">
	<form>
		<div class="form-group">
			<label for="username">Username :</label> <input type="text" class="form-control" placeholder="Enter Username" id="username" value="${principal.user.username }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control"  id="password"  value="password"readonly="readonly">
		</div>
		<div class="form-group">
			<label for="email">Role :</label> <input type="email" class="form-control" placeholder="Enter Email" id="role" value="${principal.user.role }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="email">Email :</label> <input type="email" class="form-control" placeholder="Enter Email" id="email" value="${principal.user.email }" readonly="readonly">
		</div>



	</form>
	<div style="display: flex; justify-content: space-between;">
		<button class="btn btn-outline-dark"  onclick="location.href='/'">돌아가기</button>
		<c:if test="${principal.user.role=='USER' }">
		<button  class="btn btn-outline-dark"  onclick="location.href='/user/updateForm'">회원정보수정</button>
		</c:if>
	</div>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>