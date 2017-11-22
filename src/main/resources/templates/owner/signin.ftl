<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1" />
	<title>商家登录</title>
	<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
	<link href="${request.contextPath}/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${request.contextPath}/bootsnipp/flat.css" rel="stylesheet" />
	
</head>
<body>
	<div class="container-fluid">
		
		<nav class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">IBC</a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li class="active"><a href="#">Dashboard</a></li>
	            <li><a href="#">帮助</a></li>
	          </ul>
	        </div>
	      </div>
	    </nav>
	    <div class="container-fluid">
	      <div class="row">
	        <div class="login-form" style="padding: 20px;">
	            <div class="form-group">
	              <input type="text" id="username" class="form-control login-field" 
	              	value="" placeholder="username" />
	              <label class="login-field-icon fui-user" for="login-name"></label>
	            </div>
	
	            <div class="form-group">
	              <input type="password" id="password" class="form-control login-field" 
	              	value="" placeholder="password" />
	              <label class="login-field-icon fui-lock" for="login-pass"></label>
	            </div>
	
	            <button id="loginBtn" class="btn btn-info btn-block"
	            	onclick="signin()">登&nbsp;录</button>
          	</div>
	      </div>
	    </div>
	</div>
	
	<script src="${request.contextPath}/js/jquery-3.2.0.min.js"></script>
	<script src="${request.contextPath}/js/md5.js"></script>
	<!--[if lt IE 9]>
      	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
	function signin() {
		var username = $("#username").val();
		var password = $("#password").val();
		if(!username || !password) {
			alert(" 用户名和密码不能为空.");
			return false;
		} else {
			$.ajax({
    			url: '${request.contextPath}/owner/signinAction',
    			data: 'username=' + username + '&password=' + faultylabs.MD5(password),
    			dataType: 'json',
    			type: 'POST',
    			success: function(data) {
    				if(data == 0) {
    					alert(" 用户名不存在或密码错误. ");
    				} else {
    					location = "${request.contextPath}/owner/";
    				}
    			}
    		});
		}
	}
	</script>
</body>
</html>