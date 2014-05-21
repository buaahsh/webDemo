
/*!
 * Control the user login
 * By:HuangShaohan	
 * Date: 2014-03-31T21:10Z
 */

$("body").on("click","button#userLogin",function(){
	login();
});

$("body").on("click","a#logout",function(){
	var r=confirm("确定注销?");
	if (r==true){
		userLogout();
	}
});

isLogin();

$(document).ready(function(){
	userState();
});

function login(){
	var user = $("input#user")[0].value;
	var password = $("input#password")[0].value;
	var json={"user":user,"password":password};
    var post={data:JSON.stringify(json)};
    $.post("/webDemo/UserLoginServlet",post,function(result){
    	if (result == "1")
    	{
    		$.cookie('user', user);
    		location.reload();
    	}
    	else
		{
    		alert("用户名或者密码错误");
    		$("input#password")[0].value= "";
		}
    });
}

function isLogin(){
	var userName = $.cookie("user");
	if (userName == null)
    {
    	if (window.location.href.indexOf("index.html") == -1 )
    	{
    		$("div.row").empty();
    		alert("请登录");
    		window.location = "index.html";
    	}
    }
}

function userState(){
    var userName = $.cookie("user");
    $("div#navbar a#logout").remove();
    $("div#navbar p").remove();
    if (userName!= null && userName.length > 0)
    {
        $("div#navbar").append("<a class='btn btn-link pull-right' id='logout'>注销</a><p class='navbar-text pull-right'>"
            +userName
            +"</p>");
        $("div#indexUser").empty().append("<h4>"+userName+"</h4><p>欢迎登陆中国国家网格！</p>");
    }
}

function userLogout(){
	var userName = $.cookie("user");
	 $.get("/webDemo/UserLogoutServlet?user="+userName,function(result){
    		$.cookie('user', null);
    		userState();
    		location.reload();
	    });
	
}
