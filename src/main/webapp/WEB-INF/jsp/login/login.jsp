<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="ui items center aligned grid">
	<div class="column">
		<h2 class="ui black image header">
			<img src="/resources/assets/images/cloud.png" class="image">
			<div class="content">
				Log-in
			</div>
		</h2>
		<form class="ui large form" id="" name="loginForn" action="/loginProc" method="post">
			<div class="ui stacked segment">
				<input type="hidden" value="${returnUrl }" name="returnUrl">
				<input type="hidden" value="${paramStr }" name="paramStr">				
				<div class="field">
					<div class="ui left icon input">
						<i class="user icon"></i>
						<input type="text" name="email" placeholder="E-mail address">
					</div>
				</div>
				<div class="field">
					<div class="ui left icon input">
						<i class="lock icon"></i>
						<input type="password" name="password" placeholder="Password">
					</div>
				</div>
				<div class="ui fluid large black submit button">Login</div>
			</div>
			
			<div class="ui error message"></div>
		
		</form>
	
		<div class="ui message">
			<!-- New to us? <a href="#">Sign Up</a> --> 
			<img src="/resources/assets/images/social login/Naver.png" class="image">
			<img src="/resources/assets/images/social login/Facebook.png" class="image">
			<img src="/resources/assets/images/social login/Kakaotalk.png" class="image">      
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('.ui.form').form({
	    	fields: {
	        	email: {
		        	identifier  : 'email',
		            rules: [
		              { type   : 'empty', prompt : '이메일 주소를 입력해 주세요.' },
		              { type   : 'email', prompt : '이메일 양식에 맞지 않습니다.' } 
		            ]
		        },
	          	password: {
		            identifier  : 'password',
		            rules: [
		              { type   : 'empty', prompt : '비밀번호를 입력해 주세요.' },
		              { type   : 'length[9]', prompt : '비밀번호는 최소 9자 이상이어야 합니다.' }
		            ]
		        }
	        }
	    });
	});
</script>
