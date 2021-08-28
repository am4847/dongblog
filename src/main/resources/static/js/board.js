let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteByNo();

		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});

	
	}
	
	, save: function() {
		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
			category: $('#category').val()
		};
		
		//console.log(data);

		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.

		}).done(function(response) {
			console.log(response);
			alert('글쓰기가 완료되었습니다.');
			location.href = "/";

		}).fail(function(error) {


			alert(JSON.stringify(error));


		});

	} 
	, deleteByNo: function() {
			let no = $('#no').text();
		if (confirm("정말 삭제하시겠습니까??") == true) {

		
		
			$.ajax({
				//회원가입 수행 요청
				type: "DELETE",
				url: "/api/board/" + no,
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.

			}).done(function(response) {
				console.log(response);
				alert('삭제가 완료되었습니다.');
				location.href = "/";

			}).fail(function(error) {


				alert(JSON.stringify(error));


			});
		}else{
		
			location.href = "/board/" +no;
		}
	}
	,update: function() {
		let data = {
			no: $('#no').val(),
			title: $('#title').val(),
			content: $('#content').val(),
			category: $('#category').val()
		};
		//console.log(data);

		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: "/api/board",
			data: JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.

		}).done(function(response) {
			console.log(response);
			alert('글수정가 완료되었습니다.');
			location.href = "/board/" + data.no;

		}).fail(function(error) {


			alert(JSON.stringify(error));


		});

	}, replySave: function() {
		let data = {
			boardNo: $('#no').text(), 
			content: $('#reply-content').val(),
			userNo: $('#userNo').val()
		};
		console.log(data);

		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/board/reply",
			data: JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.

		}).done(function(response) {
			console.log(response);
			alert('댓글이 등록되었습니다.');
			location.href = "/board/"+data.boardNo;

		}).fail(function(error) {


			alert(JSON.stringify(error));


		});

	},
	replyDeleteByNo: function(boardNo, replyNo) {
						
		if (confirm("정말 삭제하시겠습니까??") == true) {

		
		
			$.ajax({
				//회원가입 수행 요청
				type: "DELETE",
				url: `/api/board/${boardNo}/reply/${replyNo}`,
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.

			}).done(function(response) {
				console.log(response);
				alert('삭제가 완료되었습니다.');
				location.href = `/board/${boardNo}`;

			}).fail(function(error) {


				alert(JSON.stringify(error));


			});
		}else{
		
			location.href = `/board/${boardNo}`;
		}
	} ,
	replyUpdateForm: function(replyNo, replyContent) {
			 	$("#reply-box button").prop("disabled", true);
		 updateFormDraw(replyNo, replyContent);
	
	} ,
	replyUpdate: function(replyNo) {
		
		updateReply(replyNo);
		$("#reply-box button").prop("disabled", false);
	
	}, replyUpdateCancel: function(replyNo) {
		console.log(replyNo);
			ReplyDraw(findReplyByNo(replyNo));
			$("#reply-box button").prop("disabled", false);
	}
}

function findReplyByNo(replyNo){
	let reply= null;
	let data ={
		no : replyNo
	};
	$.ajax({
			
			type: "POST",
			url: "/api/board/reply/detail",
			data: JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json", // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.
			async : false
		}).done(function(data) {
			reply = data;
		
			console.log(reply);
			console.log(data);
		

		}).fail(function(error) {


			alert(JSON.stringify(error));


		});
			console.log(reply);
	return reply;
}
function updateReply(replyNo){
	
		
		let replyContentUpdate =$("#replyContentUpdate").val();
		console.log("hi replyUpdate");
		console.log(replyNo);
		console.log(replyContentUpdate);

	
	
	
		let data = {
			no: replyNo,
			content: replyContentUpdate
			
		};
		//console.log(data);

		//ajax 호출시 default가 비동기 호출 << 쓰레드 처리된다는 이야기
		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: `/api/board/reply/update`,
			data: JSON.stringify(data),// http body 데이터 (보내는 데이터 변환 형태)
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 것이 json이라면) => javascript 데이터로 변환해준다.

		}).done(function(response) {
			console.log(response);
			alert('댓글수정이 완료되었습니다.');
			ReplyDraw(findReplyByNo(replyNo));
		
		}).fail(function(error) {


			alert(JSON.stringify(error));

	
		});
}


function updateFormDraw(replyNo, replyContent){
	$(".reply-div-"+replyNo).html("<textarea id='replyContentUpdate' class='card-text mb-0' rows='2' cols='44'>"+replyContent+"</textarea>");
	$(".reply-div-"+replyNo).append(`<button onclick="index.replyUpdate(${replyNo})"  class='btn btn-light badge'>완료</button>`);
	$(".reply-div-"+replyNo).append(`<button onclick="index.replyUpdateCancel(${replyNo})" class='btn btn-light badge'>취소</button>`);
}


function ReplyDraw(reply){
		
		$(".reply-div-"+reply.no).empty();
		$(".reply-div-"+reply.no).append(`
									<p class="card-text mb-0">${reply.content }</p>
									<div>
										<font color="lightgray" size="2">${dateFormat(reply.createDate)}</font>
										
											<button onclick="index.replyUpdateForm(${reply.no },'${reply.content }')"  class="btn btn-light badge ">수정</button>
											<button onclick="index.replyDeleteByNo(${reply.board.no},${reply.no })" class="btn btn-light badge">삭제</button>
										
									</div>
		
		`);
}
function dateFormat(createDate){

	let date = new Date(createDate);
	let year = date.getFullYear();                   
    let month = (1 + date.getMonth());                 
    month = month >= 10 ? month : '0' + month;      
     
    let day = date.getDate();                         
    day = day >= 10 ? day : '0' + day;             
     
    let hour = date.getHours();                        
    hour = hour >= 10 ? hour : '0' + hour;         
     
    let min = date.getMinutes();                      
    min = min >= 10 ? min : '0' + min;    
    
	let format = year+"."+month+"."+day+" "+hour+":"+min;
	return format;

	}
index.init();