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
					href="${request.contextPath}/admin/device/apply">继续申请设备</a>
			</h1>
			<table class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                     <tr>
                          <th>序号</th>
                          <th>批次</th>
                          <th>申请数量</th>
                          <th>申请理由</th>
                          <th>申请备注</th>
                          <th>审核状态</th>
                          <th>审核备注</th>
                          <th>创建时间</th>
                          <th>状态</th>
                     </tr>
                </thead>
                <tbody>
                	<#list acList as ac>
	                	<tr>
	                		<td>${ac?counter}</td>
	                		<td>${ac.apply.applyId!'-'}</td>
	                		<td>${ac.apply.quantity}</td>
	                		<td>${ac.apply.reason}</td>
	                		<td>${ac.apply.comment}</td>
	                		<td>
		                		<#if ac.apply.auditStatus??>
		                			<#if ac.apply.auditStatus == 0>审核未通过
		                			<#elseif ac.apply.auditStatus == 1>审核中
		                			<#elseif ac.apply.auditStatus == 2>审核已通过
								 	</#if>
								<#else>${ac.apply.errcode}
		                		</#if>
	                		</td>
	                		<td>
	                			<#if ac.apply.auditStatus??>${ac.apply.auditComment!'-'}
								<#else>${ac.apply.errmsg}
		                		</#if>
	                		</td>
	                		<td>${ac.createTime}</td>
	                		<td>
	                			<#if ac.apply.status == 0>已完成
	                			<#elseif ac.apply.status == 1>申请中
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