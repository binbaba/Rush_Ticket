var xmlHttp = null;
var interval = null;
var names = [];
function createXmlHttp() {
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
	}
}

function update(){//更新电影票数
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			var ticketInformation = eval("(" + text + ")");
			var nums = ticketInformation.movieNum;
			for(var i = 0;i < nums;i++){
				var movie_name = $("#movie"+i).text();
				for(var j = 0;j < nums;j++){
					if(movie_name == ticketInformation.ticketInformation[j].movieName){
						$("#TI_nums"+i).text(ticketInformation.ticketInformation[j].nums);
						break;
					}
				}
			}
		}
	};
	xmlHttp.open("GET", "selectTicketNum.do?names="+names, true);
	xmlHttp.send();
}

function insertMovie() {//获取电影信息
	var xml = null;
	if (window.XMLHttpRequest) {
		xml = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
	} else if (window.ActiveXObject) {
		xml = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
	}
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			var text = xml.responseText;
			var movieInformation = eval("(" + text + ")");
			var movieNum = movieInformation.movieNum;
			for ( var i = 0; i < movieNum; i++) {
				var movieName = movieInformation.movieInformation[i].movieName;
				names[i] = movieName;
				var content = "<tr class=\"item\">\n<td rowspan=3><img src=\"./pic/"+movieInformation.movieInformation[i].pic+"\" width=280px height=412px>\n"+
				"</td>\n<th class=\"movie_name\" id=\"movie"+i+"\">"+movieName+"</th>\n</tr>\n<tr>\n<td class=\"movie_description\">\n"+
				"<p>"+movieInformation.movieInformation[i].type+"</p>\n"+
				"<p>导演："+movieInformation.movieInformation[i].director+"</p>\n"+
				"<p>主演："+movieInformation.movieInformation[i].actors+"</p>\n"+
				"<p>剧情："+movieInformation.movieInformation[i].plot+"</p>\n"+
				"</td>\n</tr>\n<tr>\n<td>\n<p class=\"ticket_num\">剩余票数:</p>\n"+
				"<p class=\"TI_nums\" id=\"TI_nums"+i+"\"></p>\n<input type=\"button\" value=\"抢票\" class=\"rush\" id=\"rush"+i+"\"/></td>\n</tr>\n\n";
				$(".movie").append(content);
			}
			update(names);
		}
	};
	xml.open("GET", "movieInformation.do", true);
	xml.send();
}

function setRegisterURL(){//设置注册页面
	$("a.register").attr("href","./register.html");
}

function setQQURL(){//设置QQ登录页面

}

function setXinlangURL(){//设置新浪微博登录页面

}

function loadTicketNums() {//页面加载完后
	setRegisterURL();
	setQQURL();
	setXinlangURL();
	isLogined();
	createXmlHttp();
	insertMovie();
	interval = setInterval("update()", 3000);
}

function isLogined() {//判断是否登录
	var xml = null;
	if (window.XMLHttpRequest) {
		xml = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
	} else if (window.ActiveXObject) {
		xml = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
	}

	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			var text = xml.responseText;
			if(text != "false") {
				if(!($("a").hasClass("loginedUserName"))){
					$("body").append("<p class=\"loginedUserName\">当前登录用户：<a>"+text+"</a>&nbsp;&nbsp;<a class=\"quit\">退出</a></p>");
				}
			}
		}
	};
	xml.open("GET", "isLogined.do", true);
	xml.send();
}

$(document).ready(function(){
	$(".movie").on("click",".rush",function(e){//后面加入的元素使用on方法来操作,点击投票，如已登录则抢票，如未登录，进行登录
		var xml = null;
		if (window.XMLHttpRequest) {
			xml = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
		} else if (window.ActiveXObject) {
			xml = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
		}

		xml.onreadystatechange = function() {
			if (xml.readyState == 4 && xml.status == 200) {
				var text = xml.responseText;
				if(text == "false") {//未登录
					var scrolltop=$(document).scrollTop();
					$(".box").css( {"width":"100%",
						"height":"101.5%", 
						"margin":"0 auto",
						"z-index":"5",
						"position":"absolute",
						"top":scrolltop-10+"px",
						"display":"none",
						"background":"rgb(192, 192, 192)"});
					$('.box').show();
					center();
					if(interval){
						clearInterval(interval);
						interval = null;
					}
				} else{//已登录
					var thisObj = e.target;//获取当前点击的电影对象
					var id = $(thisObj).attr("id");
					var movieid = "movie"+id.substr(4);
					var thisName = $("#"+movieid).text();
					rushTicket(thisName);
				}
			}
		};
		xml.open("GET", "isLogined.do", true);
		xml.send();
	});
	
	function rushTicket(rushName){
		var xml = null;
		if (window.XMLHttpRequest) {
			xml = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
		} else if (window.ActiveXObject) {
			xml = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
		}

		xml.onreadystatechange = function() {
			if (xml.readyState == 4 && xml.status == 200) {
				var text = xml.responseText;
				alert(text);
				if(text != "大家太热情了，电影票已被抢光") update(); 
			}
		};
		xml.open("GET", "rushTicketController.do?movieName="+rushName, true);
		xml.send();
	}

	function center(){//登录页面居中
		$(window).scroll(function(){
			var scrolltop=$(document).scrollTop();
			$(".box").css( {"width":"100%",
				"height":"101.5%", 
				"margin":"0 auto",
				"z-index":"5",
				"position":"absolute",
				"top":scrolltop-10+"px",
				"background":"rgb(192, 192, 192)"});
		});
	}
	
	$(document).on("click",".quit",function(e){
		var xml = null;
		if (window.XMLHttpRequest) {
			xml = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
		} else if (window.ActiveXObject) {
			xml = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
		}

		xml.onreadystatechange = function() {
			if (xml.readyState == 4 && xml.status == 200) {
				window.location.reload();				
			}
		};
		xml.open("GET", "quitController.do", true);
		xml.send();
	});
});

$(document).ready(function(){
	$('.login_title a').click(function(){//关闭登录页面	
		$('.box').hide();
		if(interval == null){
			interval = setInterval("update()", 3000);
		}
		if(($("b").hasClass("name_null"))){
			$("b").remove(".name_null");
		}
		if(($("b").hasClass("password_null"))){
			$("b").remove(".password_null");
		}
	});
});

$(document).ready(function() {
	$(".login").click(function() {
		if($("#user_name").val().length == 0){//登录时检查用户名
			if(!($("b").hasClass("name_null"))){
				$("#div_username").append("<b class=\"name_null\" color=\"red\">请填写用户名！</b>");
			}
			return false;
		}

		if($("#password").val().length == 0){//登录时检查密码
			if(!($("b").hasClass("password_null"))){
				$("#div_password").append("<b class=\"password_null\" color=\"red\">请填写密码！</b>");
			}
			return false;
		}

		//登录
		var xmlh=null;
		if (window.XMLHttpRequest) {
			xmlh = new XMLHttpRequest();//Firefox, Chrome, Opera, Safari,IE7,IE8
		} else if (window.ActiveXObject) {
			xmlh = new ActiveXObject("Microsoft.XMLHTTP");//IE6,IE5
		}

		var name = $("#user_name").val();
		var password = $("#password").val();
		xmlh.open("POST", "login.do", true);
		xmlh.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlh.onreadystatechange = function() {
			if (xmlh.readyState == 4 && xmlh.status == 200) {
				var text = xmlh.responseText;
				if(text == "false"){//用户名或密码不正确
					if(!($("b").hasClass("bothError"))){
						$("#div_password").append("<b class=\"bothError\" color=\"red\">用户名或密码有误！</b>");
					}
				}else{//登录成功
					$('.box').hide();
					if(!($("a").hasClass("loginedUserName"))){
						$("body").append("<p class=\"loginedUserName\">当前登录用户：<a>"+name+"</a>&nbsp;&nbsp;<a class=\"quit\">退出</a></p>");//??????????
					}
					if(interval == null){
						interval = setInterval("update()", 3000);
					}
				}
			}
		};
		xmlh.send("name="+name+"&password="+password);

	});

	$("#user_name").blur(function(){//用户名检查
		if(($("b").hasClass("name_null"))){
			$("b").remove(".name_null");
		}

		if(($("b").hasClass("bothError"))){
			$("b").remove(".bothError");
		}

		if($("#user_name").val().length == 0){
			if(!($("b").hasClass("name_null"))){
				$("#div_username").append("<b class=\"name_null\" color=\"red\">请填写用户名！</b>");
			}
		}
	});

	$("#password").blur(function(){//密码检查
		if(($("b").hasClass("password_null"))){
			$("b").remove(".password_null");
		}

		if(($("b").hasClass("bothError"))){
			$("b").remove(".bothError");
		}

		if($("#password").val().length == 0){
			if(!($("b").hasClass("password_null"))){
				$("#div_password").append("<b class=\"password_null\" color=\"red\">请填写密码！</b>");
			}
		}
	});

});