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
			<h1 class="page-header">代理商列表
				<a class="btn btn-info pull-right" 
					href="${request.contextPath}/admin/agent/add">新增代理商</a>
			</h1>
			<table class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                     <tr>
                          <th>序号</th>
                          <th>代理商名称</th>
                          <th>联系人</th>
                          <th>联系电话</th>
                          <th>结算比例</th>
                          <th>账户余额</th>
                          <th>冻结金额</th>
                          <th>累计支出</th>
                          <th>累计收入</th>
                          <th>ibeacon数量</th>
                          <th>创建时间</th>
                          <th>状态</th>
                          <th>操作</th>
                     </tr>
                </thead>
                <tbody>
                	<#list acList as ac>
	                	<tr>
	                		<td>${ac?counter}</td>
	                		<td>${ac.agent.name}</td>
	                		<td>${ac.agent.contact}</td>
	                		<td>${ac.agent.phone}</td>
	                		<td>${ac.agent.settlementRatio * 100}%</td>
	                		<td>${ac.agent.amountTotal?string('0.00')}</td>
	                		<td>${ac.agent.amountFreezed?string('0.00')}</td>
	                		<td>${ac.agent.amountOut?string('0.00')}</td>
	                		<td>${ac.agent.amountIn?string('0.00')}</td>
	                		<td>${ac.deviceList?size}</td>
	                		<td>${ac.createTime}</td>
	                		<td>
	                			<#if ac.agent.status == 0>
	                				<span class="label label-danger">禁用</span>
							 	<#elseif ac.agent.status == 1>
							 		<span class="label label-success">启用</span>
							 	</#if>
	                		</td>
	                		<td>
	                			<div class="btn-group">
								  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    更多 <span class="caret"></span>
								  </button>
								  <ul class="dropdown-menu dropdown-menu-right">
								  	<li><a href="#">分配设备</a></li>
								  	<#if ac.deviceList?size gt 0>
		                				<li><a href="#" onclick="">回收设备</a></li>
		                			</#if>
								  	<#if ac.agent.status == 0>
								  		<li><a href="#" onclick="allow(${ac.agent.id})">启用代理商</a></li>
								  	<#elseif ac.agent.status == 1>
								  		<li><a href="#" onclick="forbid(${ac.agent.id})">禁用代理商</a></li>
								  	</#if>
								    <li><a href="#">编辑信息</a></li>
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
		var url = '${request.contextPath}/admin/agent/allowAction/' + id;
		$.get(url, function(data) {
			location.reload();
		});
	}
	
	function forbid(id) {
		var url = '${request.contextPath}/admin/agent/forbidAction/' + id;
		$.get(url, function(data) {
			location.reload();
		});
	}
	</script>
</body>
</html>