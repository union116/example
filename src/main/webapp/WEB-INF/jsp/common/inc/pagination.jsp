<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 	
	<div class="ui right floated pagination menu">	     
		<a class="icon item">
			<i class="left chevron icon"></i>
		</a>
	    <a class="item">1</a>
	    <div class="item">
	  		...
		</div>
	     
	    <a class="item">7</a>
	    <a class="active item">8</a>
	    <a class="item">9</a>
	    <div class="disabled item">
	  		...
		</div>
		<a class="active item">27</a>	
	  	<a class="icon item">
	    	<i class="right chevron icon"></i>
	  	</a>
	</div>
 -->			
<%-- 
totalPageCount : ${paginationInfo.totalPageCount}
pageSize : ${paginationInfo.pageSize }
firstPageNoOnPageList : ${paginationInfo.firstPageNoOnPageList}
firstPageNo : ${paginationInfo.firstPageNo}
firstPageNoOnPageList : ${paginationInfo.firstPageNoOnPageList}
lastPageNoOnPageList : ${paginationInfo.lastPageNoOnPageList }
currentPageNo : ${paginationInfo.currentPageNo }
lastPageNo : ${paginationInfo.lastPageNo}
 --%>

<div class="ui right floated pagination menu">

	<c:if test="${paginationInfo.totalPageCount gt paginationInfo.pageSize }">
		<c:choose>
			<c:when test="${paginationInfo.firstPageNoOnPageList gt paginationInfo.pageSize }">
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.firstPageNo}); return false;">				
					<i class="left angle double left icon"></i>
				</a>
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.firstPageNoOnPageList - 1}); return false;">
					<i class="left angle left icon"></i>
				</a>
			</c:when>
			<c:otherwise>
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.firstPageNo}); return false;">
					<i class="left angle double left icon"></i>
				</a>
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.firstPageNo}); return false;">
					<i class="left angle left icon"></i>
				</a>
			</c:otherwise>
		</c:choose>		
	</c:if>

	<c:forEach var="i" begin="${paginationInfo.firstPageNoOnPageList}" end="${paginationInfo.lastPageNoOnPageList }" varStatus="status">
		<c:choose>
			<c:when test="${i eq paginationInfo.currentPageNo }">
				<a class="active item">${i}</a>	
			</c:when>
			<c:otherwise>
				<a class="item" href="#" onclick="moveToList(${i}); return false;">${i}</a>
			</c:otherwise>
		</c:choose>			
	</c:forEach> 
	
	<c:if test="${paginationInfo.totalPageCount gt paginationInfo.pageSize }">
		<c:choose>
			<c:when test="${paginationInfo.lastPageNoOnPageList lt paginationInfo.totalPageCount }">
				<!-- 다음 페이지 리스트의 첫번째 게시물 번호 --> 
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.firstPageNoOnPageList + paginationInfo.pageSize}); return false;">				
					<i class="right angle right icon"></i>
				</a>
				<%-- <a class="icon item" href="#" onclick="moveToList(${paginationInfo.firstPageNoOnPageList + 1}); return false;">				
					<i class="right angle right icon"></i>
				</a> --%>
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.lastPageNo}); return false;">
					<i class="right angle double right icon"></i>
				</a>
			</c:when>
			<c:otherwise>
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.lastPageNo}); return false;">
					<i class="right angle right icon"></i>
				</a>
				<a class="icon item" href="#" onclick="moveToList(${paginationInfo.lastPageNo}); return false;">
					<i class="right angle double right icon"></i>
				</a>
			</c:otherwise>
		</c:choose>		
	</c:if>
	
</div>