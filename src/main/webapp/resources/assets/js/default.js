/**
 * 공통 script
 */


/**
 * type : success, info, warning, error
 * message : text message
 */
function showAlert(type, title, message){
	
	if(type == null || type == ""){
		type = "info";
	}
	
	if(title == null || title == ""){
		title = "정보";
	}
	
	// type 에 따라 뭘 바꿀까아~~~
	
	var p = document.createElement('p');
	var text = document.createTextNode(message);
	p.appendChild(text);
	
	$('#mini_alert_header').html(title);
	$('#mini_alert_content').html(p);
	
	$('#mini_alert').modal('show');
};

