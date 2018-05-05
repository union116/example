
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/inc/taglibs.jspf"%>

<div class="ui divided items main container">	

	<div class="ui right floated small basic icon buttons">
		 <button class="ui button" data-tooltip="등록" data-position="bottom right" data-inverted="" onclick="javascript:moveToAdd();" ><i class="save icon"></i></button>
<!-- 		<button class="ui button"><i class="file icon"></i></button>
		<button class="ui button"><i class="save icon"></i></button>
		<button class="ui button"><i class="upload icon"></i></button>
		<button class="ui button"><i class="download icon"></i></button> -->
	</div>


	<c:if test="${not empty mainList }">
		<c:forEach items="${mainList }" var="main" varStatus="status">				
			<div class="item" <c:if test="${status.first }"> id="item_first_child"</c:if> >
				<div class="content">
					<a class="header" href="javascript:moveToView(${main.no })">${main.title }</a>
					<div class="meta">
						<span>${main.regDt }</span>
					</div>
					<div class="description">
						<p></p>
					</div>			
				</div>		
			</div>	
		</c:forEach>	
	</c:if>
	<c:if test="${empty mainList }">
		<div class="item" id="item_first_child">
			<div class="content">
				<div class="header">No Data...!</div>
				<div class="meta">
					<span><%-- ${sfn:getToday('yyyy-MM-dd EEE')}요일 --%></span>
				</div>
				<div class="description">
					<p></p>
				</div>
			</div>
		</div>
	</c:if>
	
	<form action="" id="listForm" method="post"></form> 
 	<%@ include file="/WEB-INF/jsp/common/inc/pagination.jsp"%>
 	
</div>

<script>
	
	function moveToAdd(){
		$(location).attr('href','/main/add');
	};
	
	function moveToView(no){		
		var html = '<input type="hidden" value="' + no + '" name="no">';				
		
		$('#listForm').html(html);
		$('#listForm').attr('action', '/main/view');
		$('#listForm').submit();
	};
	
	function moveToList(pgno){		
		var html = '<input type="hidden" value="' + pgno + '" name="pgno">';				
		
		$('#listForm').html(html);
		$('#listForm').attr('action', '/main/list');
		$('#listForm').submit();
	};
	
</script>

