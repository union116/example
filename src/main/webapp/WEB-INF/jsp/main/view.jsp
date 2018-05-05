<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/inc/taglibs.jspf"%>

<div class="ui main intro items container">

	<c:if test="${not empty mainVo}">
		<%-- <input type="hidden" value="${mainVo.no }" name="no"> --%>
		<h2 class="ui dividing header">${mainVo.title }</h2>
		${mainVo.content }		
	</c:if>
	
	<c:if test="${empty mainVo}">
		<h2 class="ui dividing header">No Data...!</h2>
		<p class="text_gray">empty</p>
	</c:if>
	
	<div class="ui right floated small basic icon buttons">	
		<button class="ui button" data-tooltip="목록" data-position="top right" data-inverted="" onclick="moveToList(1);"><i class="list alternate outline icon"></i></button>
		<button class="ui button" data-tooltip="수정" data-position="top right" data-inverted="" onclick="moveToUpdate(${mainVo.no });"><i class="edit outline icon"></i></button>
		<button class="ui button" data-tooltip="삭제" data-position="top right" data-inverted="" onclick="deleteBbs(${mainVo.no });"><i class="trash alternate outline icon"></i></button>
	</div>
		
	<!-- submit form -->
	<form action="" id="viewForm" method="post"></form>	
	
	<!-- delete modal -->	
	<div class="ui basic modal" id="deleteModal">
		<div class="ui icon header">
			<i class="trash icon"></i>
			게시물 삭제 요청
		</div>
		<div class="content">			
			<p>이 게시물을 정말로 삭제할까요?</p>
		</div>
		<div class="actions">
			<div class="ui red basic cancel inverted button">
				<i class="remove icon"></i>
				No
			</div>
			<div class="ui green ok inverted button">
				<i class="checkmark icon"></i>
				Yes
			</div>
		</div>
	</div>	
		
</div>

<script>

	// 목록 이동
	function moveToList(pgno){
		var html = '<input type="hidden" value="' + pgno + '" name="pgno">';				
		
		$('#viewForm').html(html);
		$('#viewForm').attr('action', '/main/list');
		$('#viewForm').submit();
	};

	// 게시물 삭제
	function deleteBbs(no){	
		if(!no){
			showAlert('error', '오류', '게시물 번호가 없습니다. 관리자에게 문의바랍니다.');
		}else{
			$('#deleteModal').modal({
			    onApprove : function() {
			    	var html = '<input type="hidden" value="' + no + '" name="no">';			
					
					$('#viewForm').html(html);
					$('#viewForm').attr('action', '/main/delete');
					$('#viewForm').submit();
			    }
			}).modal('show');			
		};		
	};
	
	// 게시물 수정
	function moveToUpdate(no){		
		if(!no){
			showAlert('error', '오류', '게시물 번호가 없습니다. 관리자에게 문의바랍니다.');
		}else{
	    	var html = '<input type="hidden" value="' + no + '" name="no">';				
			
			$('#viewForm').html(html);
			$('#viewForm').attr('action', '/main/update');
			$('#viewForm').submit();			    			
		};
	};
	
	
</script>

