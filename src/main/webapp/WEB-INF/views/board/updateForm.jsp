<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 700px;">
	<form>
		<div class="form-group">
			<label for="title">TITLE</label> <input value="${board.title }" type="text" class="form-control" placeholder="Enter TITLE" id="title">
		</div>
		<div class="form-group" style="width: 200px">
			<label for="">CATEGORY:</label> <select selected="" ${board.category } value="" class="form-control" id="category">
				<option>DIARY</option>
				<option>DEVELOP</option>
				<option>BUSINESS</option>
				<option>MEMO</option>
			</select>
		</div>
		<div class="form-group">
			<label for="content">CONTENT</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content }</textarea>
		</div>


	</form>
	<input type="hidden" id="id" value="${board.id }" />
	<div style="display: flex; justify-content: space-between;">
<!-- 	<div style="display: flex; align-items: flex-end; flex-direction: column;"> -->
	<button class="btn btn-outline-dark"  onclick="javascript:btn_history()" >돌아가기</button>
		<button id="btn-update" class="btn btn-outline-dark">내용수정</button>
	</div>
</div>

<script>
	$('.summernote').summernote({

		tabsize : 2,
		height : 300
	});
	function btn_history(){
		if(confirm('사이트에서 나가시겠습니까?\n변경사항이 저장되지 않을 수 있습니다.')==true)location.href="/board/"+${board.id};  
			}
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>