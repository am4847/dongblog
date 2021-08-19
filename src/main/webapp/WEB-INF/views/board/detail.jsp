<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 700px;">

	<%-- 	<div class="form-group">

		<label for="title">TITLE</label>
		<h3>${board.title }</h3>

	</div>
	<div>
		글번호 : <span id="id"> <i>${board.id }</i></span> 작성자 : <span> <i>${board.user.username }</i></span>
	</div>
	<div class="form-group">
		<label for="content">CONTENT</label>
		<div>${board.content }</div>
	</div>

 --%>

	<div class="margin-top first ">

		<div style="text-align: right">
			<span style="font-size: 30px; font-weight: 500;">${board.category }</span>
			<span style="color: gray; font-size: 15px; margin-left: 5px;">category</span> 

		</div>

		<table class="table ">
			<tbody>
				<tr>

					<th class="text-align-left text-indent text-strong text-orange" colspan="6" style="font-size: 30px">${board.title }</th>
				</tr>
				<tr>
					<th>작성자</th>
					<td class="text-align-left text-indent" colspan="6">${board.user.username }</td>
				</tr>
				<tr>
					<th>NO</th>
					<td id="id">${board.id }</td>
					<th>작성일</th>
					<td><fmt:formatDate value="${board.createDate }" pattern="yyyy.MM.dd hh:mm" /></td>
					<th>조회수</th>
					<td>${board.count }</td>
				</tr>

				<tr class="content">
					<td colspan="6">${board.content }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%-- <input type="hidden" value="${board.id }"> --%>
	<button class="btn btn-outline-dark" onclick="history.back()">돌아가기</button>

	<c:if test="${board.user.id==principal.user.id }">
		<a href="/board/${board.id }/updateForm" class="btn btn-outline-dark">수정</a>
		<button id="btn-delete" class="btn btn-outline-dark">삭제</button>
	</c:if>


</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>