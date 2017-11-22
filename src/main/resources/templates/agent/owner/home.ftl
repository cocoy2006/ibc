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
			<h1 class="page-header">商家列表
				<a class="btn btn-info pull-right" 
					href="${request.contextPath}/agent/owner/add">新增商家</a>
			</h1>
			<table class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                     <tr>
                          <th>序号</th>
                          <th>商家名称</th>
                          <th>代理商名称</th>
                          <th>行业</th>
                          <th>账户金额</th>
                          <th>累计支出</th>
                          <th>创建时间</th>
                          <th>状态</th>
                          <th>操作</th>
                     </tr>
                </thead>
                <tbody>
                	<#list ocList as oc>
	                	<tr>
	                		<td>${oc?counter}</td>
	                		<td>${oc.owner.name}</td>
	                		<td>${oc.agent.name}</td>
	                		<td>${oc.industry.name}-${oc.subIndustry.name}</td>
	                		<td>${oc.owner.amountTotal}</td>
	                		<td>${oc.owner.amountOut}</td>
	                		<td>${oc.createTime}</td>
	                		<td>
	                			<#if oc.owner.status == 0>
	                				<span class="label label-danger">禁用</span>
							 	<#elseif oc.owner.status == 1>
							 		<span class="label label-success">启用</span>
							 	</#if>
	                		</td>
	                		<td>
	                			<div class="btn-group">
								  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    更多 <span class="caret"></span>
								  </button>
								  <ul class="dropdown-menu dropdown-menu-right">
								  	<#if oc.owner.status == 0>
								  		<li><a href="#" onclick="allow(${oc.owner.id})">启用商家</a></li>
								  	<#elseif oc.owner.status == 1>
								  		<li><a href="#" onclick="forbid(${oc.owner.id})">禁用商家</a></li>
								  	</#if>
								    <li><a href="#">编辑</a></li>
								    <li><a href="#">删除</a></li>
								  </ul>
								</div>
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

	function allow(id) {
		var url = '${request.contextPath}/agent/owner/allowAction/' + id;
		$.get(url, function(data) {
			location.reload();
		});
	}
	
	function forbid(id) {
		var url = '${request.contextPath}/agent/owner/forbidAction/' + id;
		$.get(url, function(data) {
			location.reload();
		});
	}
	</script>
</body>
</html>