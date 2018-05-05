<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/inc/taglibs.jspf"%>

<!DOCTYPE html>
<html>
	<head>
		<!-- Standard Meta -->
		<meta charset="UTF-8">		
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="Content-Language" content="ko">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" /> 
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<!-- <meta http-equiv="Refresh" content="60" /> -->
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
		
		<!-- Site Properties -->
		<title>example</title>
		
		<!-- 운영 -->		
		<!-- <link rel="stylesheet" type="text/css" href="/resources/assets/vendor/Semantic-UI-CSS-master/semantic.min.css"> -->
		<!-- 개발 -->
		<link rel="stylesheet" type="text/css" href="/resources/assets/vendor/Semantic-UI-CSS-master/semantic.css">
		<link rel="stylesheet" type="text/css" href="/resources/assets/css/default.css">
		<script src="/resources/assets/js/jquery-3.2.1.js"></script>
		<script src="/resources/assets/js/default.js"></script>
		<!-- 운영 -->
		<!-- <script src="/resources/assets/vendor/Semantic-UI-CSS-master/semantic.min.js"></script> -->
		<!-- 개발 -->
		<script src="/resources/assets/vendor/Semantic-UI-CSS-master/semantic.js"></script>
		
		<script>
		$(document).ready(function() {
		
		    // fix menu when passed
		    /* $('.masthead')
		      .visibility({
		        once: false,
		        onBottomPassed: function() {
		          $('.fixed.menu').transition('fade in');
		        },
		        onBottomPassedReverse: function() {
		          $('.fixed.menu').transition('fade out');
		        }
		      }); */
		
		    // create sidebar and attach to menu open
		    $('.ui.sidebar').sidebar('attach events', '.toc.item');
		
		  });
		</script>
				 
	</head>
	
	<body id="example" class="pushable">
		<!-- Page Contents -->
		<tiles:insertAttribute name="left" />
		<div class="pusher">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</body>
</html>