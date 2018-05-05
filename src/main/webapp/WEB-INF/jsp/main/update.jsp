<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="ui main items container">

	<form class="ui form" id="updateForm" action="/main/modify" method="post">
		<div class="field">
			<label>제목</label>
			<input type="text" id="title" name="title" value="${mainVo.title }">
			<input type="hidden" value="${mainVo.no }" name="no">
		</div>
		<div class="field">
			<label>내용</label>
			<textarea rows="10" cols="80" id="content" name="content">${mainVo.content }</textarea>
		</div>
		<div class="ui primary submit button">수정</div>
		<div class="ui button" onclick="javascript:moveToView(${mainVo.no });">취소</div>
		<div class="ui error message"></div>
	</form>
	
	<!-- view form -->
	<form action="" id="viewForm" method="post"></form>
	
</div>

<style type="text/css">
	.cke_textarea_inline {
		border: 1px solid rgba(34, 36, 38, 0.15);
		border-radius: 0.28571429rem;
		padding: 10px;
		min-height: 300px;
	}
</style>

<script type="text/javascript" src="/resources/assets/vendor/ckeditor/ckeditor.js"></script>

<script>

	$(document).ready(function(){
	    CKEDITOR.inline( 'content' );
	    
	    $('#updateForm').form({
			on: 'blur',
			fields: {
				title: {
					identifier  : 'title',
					rules: 
						[{type   : 'empty',
						prompt : '제목을 입력해 주시기 바랍니다.'}]
				}/* ,
				content: {
					identifier  : 'content',
					rules: [{type   : 'empty',
					  		prompt : '내용을 입력해 주시기 바랍니다.'}]
				} */	       
			}
	    });
	    
	});
	
	// 상세페이지로 이동
	function moveToView(no){		
		var html = '<input type="hidden" value="' + no + '" name="no">';				
		
		$('#viewForm').html(html);
		$('#viewForm').attr('action', '/main/view');
		$('#viewForm').submit();
	};
	
</script>