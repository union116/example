<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="ui inverted vertical footer segment">
	<div class="ui container">
		<div class="ui stackable inverted divided equal height stackable grid">	   
			<div class="ui center aligned container">		
				<p>COPYRIGHT(C) 2018 union116 ALL RIGHT RESERVED.</p>		
			</div>		  
		</div>
	</div>
</div>

<div id="mini_alert" class="ui mini modal transition hidden" style="margin-top: -92.5px;">
	<div class="header" id="mini_alert_header"></div>
	<div class="content" id="mini_alert_content"></div>
	<div class="actions">
		<div class="ui ok button">확인</div>
	</div>
</div>

<script>
	$(document).ready(function() {
				
		// toast는 무엇? 모바일용??
		var toastMode = "${toastMode}";		
		if(toastMode == 'error' || toastMode == 'info' || toastMode == 'success' || toastMode == 'warning'){
			//showToastAlert(toastMode, "${toastPosition}", "${toastTitle}", "${toastMessage}");
		}
		
		var swalMode = "${swalMode}";
		if(swalMode == 'error' || swalMode == 'info' || swalMode == 'success' || swalMode == 'warning'){
			//showSwalAlert(swalMode, "${swalTitle}", "${swalMessage}");
			showAlert(swalMode, "${swalTitle}", "${swalMessage}");			
		}
		
	});
</script>