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
			<h1 class="page-header">设备列表</h1>
			<table class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                     <tr>
                          <th>序号</th>
                          <th>major</th>
                          <th>minor</th>
                          <th>备注</th>
                          <th>状态</th>
                          <th>操作</th>
                     </tr>
                </thead>
                <tbody>
                	<#list dcList as dc>
	                	<tr>
	                		<td>${dc?counter}</td>
	                		<td>${dc.device.major?string.number}</td>
	                		<td>${dc.device.minor?string.number}</td>
	                		<td>${dc.device.comment}</td>
	                		<td>
	                			<#if dc.device.status == 3>未分配
							 	<#elseif dc.device.status == 4>未绑定活动
							 	<#elseif dc.device.status == 5>正在使用
							 	<#elseif dc.device.status == 9>已删除
							 	</#if>
	                		</td>
	                		<td>
	                			<#if dc.device.status == 3>
							  		<a href="#" onclick="openModal(${dc.device.id})">分配</a>
							  	<#elseif dc.device.status gt 3>
							  		<span>已分配商家(${dc.owner.name})</span>
							  	</#if>
	                		</td>
	                	</tr>
                	</#list>
                </tbody>
           </table>
		</div>
	</div>
	
	<!-- 商家选择的模态框 --> 
    <div id="ownerSelectionModal" class="modal fade" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
              <div class="modal-content">
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">请选择商家</h4>
                   </div>
                   <div class="modal-body">
                        <table class="table table-striped table-bordered" cellspacing="0" width="100%">
			                <thead>
			                    <tr>
			                    	<th></th>
			                    	<th>序号</th>
			                        <th>商家名称</th>
			                        <th>行业</th>
			                        <th>创建时间</th>
			                        <th>状态</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<#if ocList?size == 0>
			                		<tr>
			                			<td colspan="6" class="text-center">无可用商家
			                				[<a href="${request.contextPath}/agent/owner/add">新增商家</a>]
			                			</td>
			                		</tr>
			                	</#if>
			                	<#list ocList as oc>
				                	<tr>
				                		<td>
				                			<input type="radio" name="ownerId" value="${oc.owner.id}"/>
				                		</td>
				                		<td>${oc?counter}</td>
				                		<td>${oc.owner.name}</td>
				                		<td>${oc.industry.name}-${oc.subIndustry.name}</td>
				                		<td>${oc.createTime}</td>
				                		<td>
				                			<#if oc.owner.status == 0>禁用
										 	<#elseif oc.owner.status == 1>启用
										 	</#if>
				                		</td>
				                	</tr>
			                	</#list>
			                </tbody>
			           </table>
                   </div>
                   <div class="modal-footer">
                        <button type="button" class="btn btn-info" data-dismiss="modal" onclick="assign()">提交分配</button>
                   </div>
              </div><!-- /.modal-content -->
         </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
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
	
	var deviceId = null;
	function openModal(id) {
		deviceId = id;
		$('#ownerSelectionModal').modal();
	}
	
	function assign() {
		var ownerId = $(':checked[name="ownerId"]').val();
		if(ownerId) {
			$.ajax({
				url: '${request.contextPath}/agent/device/assignAction',
				data: 'ownerId=' + ownerId + '&deviceId=' + deviceId,
				dataType: 'json',
				type: 'POST',
				success: function(data) {
					alert('提交成功');
					location.reload();
				}
			});
		}
	}

	</script>
</body>
</html>