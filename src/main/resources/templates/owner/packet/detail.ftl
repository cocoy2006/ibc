<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
<style type="text/css">
	.box-wrap {height: 100px;}
	.box {border: solid #ccc 1px; margin-right: 20px; padding: 30px 0 30px 70px;}
	.list-group-item {height: 40px;}
</style>
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h1 class="page-header">
				<a class="btn btn-info" 
					href="#" onclick="javascript:history.back()">返回</a>
				红包明细
			</h1>
			<div class="box-wrap">
				<div class="col-xs-3 box">红包总额:<strong class="text-warning">${packet.amountTotal}元</strong></div>
				<div class="col-xs-3 box">红包余额:<strong class="text-warning">${packet.amountLeft}元</strong></div>
				<div class="col-xs-3 box">红包支出:<strong class="text-warning">${packet.amountTotal - packet.amountLeft}元</strong></div>
			</div>
			<ul class="list-group">
			  <#list bupcList as bupc>
			  	<li class="list-group-item">
			  	  <div class="col-xs-4">
			    	${bupc.user.nickname }
				  </div>
				  <div class="col-xs-4">
			    	${bupc.createTime }
				  </div>
				  <div class="col-xs-4">
			    	&plus;${bupc.bup.amount }元
				  </div>
			  	</li>
			  </#list>
			</ul>
		</div>
	</div>
	
	<script src="${request.contextPath}/js/jquery-3.2.0.min.js"></script>
	<script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
	
	</script>
</body>
</html>