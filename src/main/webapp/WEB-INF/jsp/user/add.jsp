<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form class="ui form items container" action="/user/insert" method="post">
  <!-- <p>Tell Us About Yourself</p> -->
  
	<div class="required field">
		<label>아이디</label>		
		<input type="text" name="email" placeholder="E-mail address">		
	</div>
    <div class="required field">
      	<label>비밀번호</label>
		<input type="password" name="password" placeholder="Password">		
    </div>
    <div class="required field">
      	<label>비밀번호 확인</label>  	
		<input type="password" name="passwordChk" placeholder="Password Check">
    </div>
    <div class="required field">
		<label>별명</label>
		<input placeholder="Nickname" name="nickName" type="text">
    </div>
      
	<div class="ui primary submit button">회원가입</div>
	<div class="ui reset button">초기화</div>
	<!-- <div class="ui error message"></div> -->
</form>



<!-- <div class="ui items center aligned grid">
	<div class="column">
		<h2 class="ui black image header">
			<img src="/resources/assets/images/cloud.png" class="image">
			<div class="content">
				Sign-up to your account
			</div>
		</h2>
		<form class="ui large form">
			<div class="ui stacked segment">
				<div class="field">
					<label class="align-l">Id</label>
					<div class="ui left icon input">
						<i class="user icon"></i>
						<input type="text" name="email" placeholder="E-mail address">
					</div>
				</div>
				<div class="field">
					<label class="align-l">Password</label>
					<div class="ui left icon input">
						<i class="lock icon"></i>
						<input type="password" name="password" placeholder="Password">
					</div>
				</div>
			    <div class="field">
			      	<label class="align-l">Password Check</label>
			      	<div class="ui left icon input">			
						<input type="password" name="passwordChk" placeholder="">
					</div>
			    </div>
			    <div class="field">
					<label class="align-l">Nickname</label>
					<input placeholder="Nickname" name="nickname" type="text">
			    </div>
				<div class="ui fluid large black submit button">Sign Up</div>
			</div>
			
			<div class="ui error message"></div>
		
		</form>
	
	</div>
</div>
 -->
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
		              { type   : 'length[9]', prompt : '비밀번호는 최소 9자 이상이어야 합니다.' },
		              { type   : 'regExp[/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{9,15}/]', prompt : '비밀번호는 9~15자, 영문&숫자&특수문자의 조합이어야 합니다.' }
		            ]
		        },
		        passwordChk: {
		            identifier  : 'passwordChk',
		            rules: [		              
		              { type   : 'match[password]', prompt : '비밀번호를 한번 더 입력해 주세요.' }
		            ]
		        },
		        nickName: {
		        	identifier  : 'nickName',
		            rules: [
		              { type   : 'empty', prompt : '별명을 입력해 주세요.' },
		              { type   : 'maxLength[20]', prompt : '별명은 최대 20자까지 가능합니다.' }
		              
		            ]
		        }
	        },
	        inline : true,
	        on     : 'blur'
	    });
	});
</script>
