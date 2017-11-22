<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
<link href="${request.contextPath}/DataTables-1.10.13/media/css/dataTables.bootstrap.min.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h1 class="page-header">申请记录
				<a class="btn btn-info pull-right" 
					href="${request.contextPath}/owner/recharge">继续在线充值</a>
			</h1>
			<table class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                     <tr>
                          <th>序号</th>
                          <th>充值金额</th>
                          <th>充值时间</th>
                          <th>状态</th>
                     </tr>
                </thead>
                <tbody>
                	<#list rcList as rc>
	                	<tr>
	                		<td>${rc?counter}</td>
	                		<td>${rc.recharge.amount?string('0.00')}元</td>
	                		<td>${rc.createTime}</td>
	                		<td>
	                			<#if rc.recharge.status == 0>
	                				<span class="label label-danger">充值失败</span>
	                			<#elseif rc.recharge.status == 1>
	                				<span class="label label-success">充值成功</span>
							 	</#if>
	                		</td>
	                	</tr>
                	</#list>
                </tbody>
           </table>
		</div>
	</div>
    
	<script src="${request.contextPath}/js/jquery-3.2.0.min.js"></script>
	<script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="${request.contextPath}/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
    <script src="${request.contextPath}/DataTables-1.10.13/media/js/dataTables.bootstrap.min.js"></script>
	<script>
	$(function() {
		$('table').DataTable({
    		"language": {
                "url": "${request.contextPath}/DataTables-1.10.13/chinese.json"
            },
            "ordering": false
    	});
	});

	</script>
</body>
</html>