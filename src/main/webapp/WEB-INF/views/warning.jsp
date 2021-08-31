<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="container" style="max-width: 500px;">

	<div class="card m-2 ">
		<div class="card-body" style="padding: 10px">
			<table class="table table-borderless mb-0">
			
				<thead class="thead-dark">
					<tr>
						<th style="font-size: 20px; width: 90%; text-align: center;">${message }</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
					<td style="text-align: center;">	<button onclick="location.href='/'" class="btn btn-outline-dark btn-block" style="font-size: 25px; ">돌아가기</button></td>
					
					</tr>
				</tbody>
				
			</table>

		</div>
	</div>

</div>
<%@ include file="layout/footer.jsp"%>