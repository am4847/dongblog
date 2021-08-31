<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="container" style="max-width: 500px; ">
	<c:forEach var="board" items="${boards.content }">
		<%-- <div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title }</h4>
				<div>writer: ${board.user.username }</div>
				<div>category: ${board.category }</div>
				<a style="font-size: 25px;" href="/board/${board.id }" class="btn btn-outline-dark">상세보기</a>



			</div>
		</div> --%>
		<div class="card m-2 btn-light "  onclick="location.href='/board/${board.no }'">
			<div class="card-body" style="padding: 10px">
				<table class="table table-borderless mb-0">
					<thead class="thead-dark">
						<tr>
							<th colspan="3" style="font-size: 20px;  width: 90%;" >${board.title }</th>
							<th style="text-align: right; font-size: 12px;  width: 10%;"><fmt:formatDate value="${board.createDate }" pattern="yyyy.MM.dd " /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td  style="font-size: 20px;  width: 15%; font-family: fantasy; ">WIRTER</td>
							<td style="font-size: 20px; padding-left:0px; width: 35%; ">${board.user.userName }</td>
							<td style="font-size: 20px; width: 15%;  font-family: fantasy;"> CATEGORY</td>
							<td style="font-size: 20px; padding-left:0px; width: 35%;">${board.category }</td>
						</tr>
						<tr>
							
						
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