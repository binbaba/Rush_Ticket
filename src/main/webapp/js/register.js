var xmlHttp = null;

function createXmlHttp() {
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
	}
}

$(document).ready(function(){
	createXmlHttp();

	$(".submit").attr("disabled","disabled");

	$("#repassword").attr("disabled","disabled");

	$(".register_user_name").blur(function(){
		var user_name = $(".register_user_name").val();
		if(user_name.length != 0){
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var isCludeUserName = xmlHttp.responseText;
					if(isCludeUserName == "true"){//包含
						if(($("b").hasClass("name_wrong"))){
							$("b").remove(".name_wrong");
						}
						if(!($("b").hasClass("includename_wrong"))){
							$(".p_name").append("<b class=\"includename_wrong\" color=\"red\">已有</b>");
						}
						$(".submit").attr("disabled","disabled");
					}else{//不包含
						if(($("b").hasClass("name_wrong"))){
							$("b").remove(".name_wrong");
						}
						if(($("b").hasClass("includename_wrong"))){
							$("b").remove(".includename_wrong");
						}
						if($("#email").val() != "" && $(".register_password").val() != "" ) {
							$(".submit").attr("disabled",false);
						}
					}
				}
			};
			xmlHttp.open("GET", "isCludeUserName.do?user_name="+user_name, true);
			xmlHttp.send();
		}else{
			if(($("b").hasClass("includename_wrong"))){
				$("b").remove(".includename_wrong");
			}
			if(!($("b").hasClass("name_wrong"))){
				$(".p_name").append("<b class=\"name_wrong\" color=\"red\">错误</b>");
			}
			$(".submit").attr("disabled","disabled");
		}
	});

	$(".register_email").blur(function(){
		var email = $(".register_email").val();
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(com)$/;
		if(!myreg.test(email))//don't
		{
			if(($("b").hasClass("includeEmail_wrong"))){
				$("b").remove(".includeEmail_wrong");
			}
			if(!($("b").hasClass("email_wrong"))){
				$(".p_email").append("<b class=\"email_wrong\" color=\"red\">错误</b>");
			}
			$(".register_email").val("");
			$(".submit").attr("disabled","disabled");
		}else{		
			xmlHttp.onreadystatechange = function() {//审核是否已存在邮箱
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var isCludeUserName = xmlHttp.responseText;
					if(isCludeUserName == "true"){//包含
						if(($("b").hasClass("email_wrong"))){
							$("b").remove(".email_wrong");
						}
						if(!($("b").hasClass("includeEmail_wrong"))){
							$(".p_email").append("<b class=\"includeEmail_wrong\" color=\"red\">已有</b>");
						}
						$(".submit").attr("disabled","disabled");
					}else{//不包含
						if(($("b").hasClass("email_wrong"))){
							$("b").remove(".email_wrong");
						}
						if(($("b").hasClass("includeEmail_wrong"))){
							$("b").remove(".includeEmail_wrong");
						}
						if($(".register_user_name").val() != "" && $(".register_password").val() != "" ) {
							$(".submit").attr("disabled",false);
						}
					}
				}
			};
			xmlHttp.open("GET", "isCludeUserEmail.do?email="+email, true);
			xmlHttp.send();
		}
	});

	$("#password").focus(function(){
		$("#password").val("");
		$("#repassword").val("");
		$("#repassword").attr("disabled","disabled");
	});

	$("#password").blur(function(){
		if($("#password").val().length < 6 || $("#password").val().length > 20){
			if(!($("b").hasClass("password_wrong"))){
				$(".p_password").append("<b class=\"password_wrong\" color=\"red\">错误</b>");
			}
			$(".submit").attr("disabled","disabled");
			$("#repassword").attr("disabled","disabled");
		}else{
			if(($("b").hasClass("password_wrong"))){
				$("b").remove(".password_wrong");
			}
			$("#repassword").attr("disabled",false);
		}
	});

	$("#repassword").blur(function(){
			if($("#repassword").val() != $("#password").val()){
				if(($("b").hasClass("Rpassword_wrong"))){
					$("b").remove(".Rpassword_wrong");
				}
				$("#password").val("");
				$("#repassword").val("");
				$("#repassword").attr("disabled","disabled");
				$(".submit").attr("disabled","disabled");
				if(!($("b").hasClass("Requapassword_wrong"))){
				$(".p_Rpassword").append("<b class=\"Requapassword_wrong\" color=\"red\">不等</b>");
				}
			}else{
				if(($("b").hasClass("Rpassword_wrong"))){
					$("b").remove(".Rpassword_wrong");
				}
				if(($("b").hasClass("Requapassword_wrong"))){
					$("b").remove(".Requapassword_wrong");
				}

				if($(".register_user_name").val() != "" && $(".register_email").val() != "" ) {
					$(".submit").attr("disabled",false);
				}
			}
	});

});