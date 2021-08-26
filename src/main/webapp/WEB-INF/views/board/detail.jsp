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
			<span class="categoryname">${board.category }</span> <span class="category">category</span>
		</div>


		<table class="table">
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

	<div class="d-flex  justify-content-between">
		<button class="btn btn-outline-dark" onclick="location.href='/'">돌아가기</button>
		<div>
			<c:if test="${board.user.id==principal.user.id }">
				<a href="/board/${board.id }/updateForm" class="btn btn-outline-dark">수정</a>
				<button id="btn-delete" class="btn btn-outline-dark">삭제</button>
			</c:if>
		</div>
	</div>
	<br />
	<hr />

	
	<div class="card mb-2">
		<div class="card-header bg-light">
			<i class="fa fa-comment fa"></i> 댓글
		</div>
		<input type="hidden" id="userId" value="${principal.user.username }">
		<div class="card-body">
				<div class="form-inline ml-3 mb-2" style="font-weight: bolder; font-size: 19px;">${principal.user.username}</div>
			<div class="d-flex" style="padding:0rem 1.25rem;">
				<textarea id="reply-content" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="댓글을 남겨보세요" ></textarea>
				<div class="d-flex flex-column ml-1 mt-1" style="width: 15%;height: 100%;">
					<button id="btn-reply-save" type="button" class="btn btn-outline-dark  " onClick="javascript:addReply();" style="height: 85px;">등록</button>
				</div>
			</div>
			<hr/>
			<ul id="reply-box" class="list-group list-group-flush">
				<c:forEach var="reply" items="${board.replys }">
					<li id="reply-list" class="list-group-item border-0 " style="border: 0; padding: .15rem 1.25rem;">
						<!-- ******************************************************************** -->
						<div class="flex-fill">
							<div class="card flex-row ">
								<div class="card-header replyUser ">${reply.user.username }</div>
								<div class="card-body d-flex  justify-content-between " style="padding: 10px;">
									<p class="card-text mb-0">${reply.content }</p>
									<div>
										<font color="lightgray" size="2"><fmt:formatDate value="${reply.createDate }" pattern="yyyy.MM.dd hh:mm" /></font>
										<c:if test="${principal.user.id==board.user.id || principal.user.id ==reply.user.id }">
											<button class="btn btn-light badge">삭제</button>
										</c:if>

									</div>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>



		</div>
	</div>



</div>


<script src="/js/board.js"></script>
<link rel="stylesheet" type="text/css" href="/css/board.css">
<%@ include file="../layout/footer.jsp"%>