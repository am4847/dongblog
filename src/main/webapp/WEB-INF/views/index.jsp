<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="container" style="max-width: 700px; ">
	<c:forEach var="board" items="${boards.content }">
		<%-- <div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title }</h4>
				<div>writer: ${board.user.username }</div>
				<div>category: ${board.category }</div>
				<a style="font-size: 25px;" href="/board/${board.id }" class="btn btn-outline-dark">상세보기</a>



			</div>
		</div> --%>
		<div class="card m-2" >
			<div class="card-body" style="padding: 10px">
				<table class="table table-borderless">
					<thead class="thead-dark">
						<tr>
							<th colspan="1" style="font-size: 20px;">${board.title }</th>
							<th style="text-align: right; font-size: 12px;"><fmt:formatDate value="${board.createDate }" pattern="yyyy.MM.dd " /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2" style="font-size: 20px; ">작성자: ${board.user.username }</td>
						</tr>
						<tr>
							<td style="font-size: 20px;">카테고리: ${board.category }</td>
							<td style="text-align: right;padding: 0;"><a style="font-size: 25px;" href="/board/${board.id }" class="btn btn-outline-dark">상세보기</a></td>
						</tr>

					</tbody>
				</table>

			</div>
		</div>




	</c:forEach>
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item" ><a class="page-link" href="?page=${boards.number-1 }" >Previous</a></li>
			</c:otherwise>


		</c:choose>
		<c:choose>
			<c:when test="${boards.last }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1 }" >Next</a></li>
			</c:otherwise>

		</c:choose>


	</ul>
</div>
<%@ include file="layout/footer.jsp"%>