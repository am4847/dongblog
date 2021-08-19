<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container" style="max-width: 700px; ">
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
	<button id="btn-update" class="btn btn-primary">내용수정</button>
</div>

<script>
	$('.summernote').summernote({

		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>