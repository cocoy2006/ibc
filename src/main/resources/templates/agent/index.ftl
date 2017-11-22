<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1" />
	<title>代理商后台</title>
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
	            <li class="active"><a href="#">${Session.agent.name!"Dashboard"}</a></li>
	            <li><a href="#">设置</a></li>
	            <li><a href="#">帮助</a></li>
	            <li><a href="#" onclick="signout()">
	            	<i class="fa fa-power-off" aria-hidden="true"></i>
	            	<span class="hidden-xs hidden-sm">退出系统</span></a></li>
	          </ul>
	        </div>
	      </div>
	    </nav>
	    <div class="container-fluid">
	      <div class="row">
	        <div class="col-sm-3 col-md-2 sidebar">
	        
	          <!-- owner -->
	          <h5><i class="fa fa-handshake-o" aria-hidden="true"></i>
                  <small><b>商家管理中心</b></small>
              </h5>
	          <ul class="nav nav-sidebar">
	            <li class="active"><a href="#" onclick="nav(this, 'owner')">
	            	<i class="fa fa-shopping-bag" aria-hidden="true"></i>
	            	<span class="hidden-xs hidden-sm">商家列表</span></a></li>
	            <li><a href="#" onclick="nav(this, 'owner/add')">
	            	<i class="fa fa-plus" aria-hidden="true"></i>
	            	<span class="hidden-xs hidden-sm">新增商家</span></a></li>
	          </ul>
	          
	          <!-- device -->
	          <h5><i class="fa fa-bluetooth" aria-hidden="true"></i>
                  <small><b>设备管理中心</b></small>
              </h5>
	          <ul class="nav nav-sidebar">
	            <li><a href="#" onclick="nav(this, 'device')">
	            	<i class="fa fa-table" aria-hidden="true"></i>
	            	<span class="hidden-xs hidden-sm">设备列表</span></a></li>
	          </ul>
	          
	          <!-- other -->
	          <h5><i class="fa fa-cog" aria-hidden="true"></i>
                  <small><b>其他</b></small>
              </h5>
	          <ul class="nav nav-sidebar">
	            <li class="disabled"><a href="#" onclick="">
	            	<i class="fa fa-area-chart" aria-hidden="true"></i>
	            	<span class="hidden-xs hidden-sm">财务中心</span></a></li>
	            <li class="disabled"><a href="#" onclick="">
	            	<i class="fa fa-user" aria-hidden="true"></i>
	            	<span class="hidden-xs hidden-sm">账户信息</span></a></li>
	          </ul>
	          
	        </div>
	        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<iframe id="main" src="owner" style="border: none; width: 100%;"
					onload="setIframeHeight(this)"></iframe>
	        </div>
	      </div>
	    </div>
	</div>
	
	<script src="${request.contextPath}/js/jquery-3.2.0.min.js"></script>
	<!--[if lt IE 9]>
      	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
	function setIframeHeight(iframe) {
		if (iframe) {
			var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
			if (iframeWin.document.body) {
				iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
				iframe.height = parseInt(iframe.height) + parseInt(150);
			}
		}
	};
		
	function nav(that, page) {
		$('.sidebar li.active').removeClass('active');
		$(that).parent().addClass('active');
		$('#main').attr('src', page);
	}

	function signout() {
		$.ajax({
			url: '${request.contextPath}/agent/signoutAction',
			dataType: 'json',
			success: function(data) {
				location = "${request.contextPath}/agent/signin";
			}
		});
	}
	</script>
</body>
</html>