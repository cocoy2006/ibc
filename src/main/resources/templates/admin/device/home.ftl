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
			<h1 class="page-header">设备列表
				<button class="btn btn-info pull-right" onclick="batchConf()">批量配置</button>
			</h1>
			<table class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                     <tr>
                     	<th>
                     		<input type="checkbox" id="checkboxAll"
			            		onclick="checkAll(this, 'ids')">
                     	</th>
                        <th>序号</th>
                        <th>批次</th>
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
	                		<td>
	                			<input type="checkbox" name="ids" value="${dc.device.id}"
	                				onclick="cancelCheckAll('checkboxAll', 'ids')"/>
	                		</td>
	                		<td>${dc?counter}</td>
	                		<td>${dc.device.applyId}</td>
	                		<td>${dc.device.major?string.number}</td>
	                		<td>${dc.device.minor?string.number}</td>
	                		<td>${dc.device.comment}</td>
	                		<td>
	                			<#if dc.device.status == 0>未知
	                			<#elseif dc.device.status == 1>未配置
	                			<#elseif dc.device.status == 2>未分配代理商
	                			<#elseif dc.device.status == 3>未分配商家
							 	<#elseif dc.device.status == 4>未绑定活动
							 	<#elseif dc.device.status == 5>正在使用
							 	<#elseif dc.device.status == 9>已删除
							 	</#if>
	                		</td>
	                		<td>
	                			<#if dc.device.status == 0>
							  		<span>异常</span>
							  	<#elseif dc.device.status == 1>
							  		<a href="#" onclick="conf(${dc.device.id})">完成配置</a>
							  	<#elseif dc.device.status == 2>
							  		<a href="#" onclick="openModal(${dc.device.id})">分配</a>
							  	<#elseif dc.device.status gt 2>
							  		<span>已分配代理商(${dc.agent.name})</span>
							  	</#if>
	                		</td>
	                	</tr>
                	</#list>
                </tbody>
           </table>
		</div>
	</div>
	
	<!-- 代理商选择的模态框 --> 
    <div id="agentSelectionModal" class="modal fade" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
              <div class="modal-content">
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">请选择代理商</h4>
                   </div>
                   <div class="modal-body">
                        <table class="table table-striped table-bordered" cellspacing="0" width="100%">
			                <thead>
			                    <tr>
			                    	<th></th>
			                    	<th>序号</th>
		                          	<th>代理商名称</th>
		                          	<th>联系人</th>
		                          	<th>联系电话</th>
		                          	<th>ibeacon数量</th>
		                          	<th>创建时间</th>
		                          	<th>状态</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<#if acList?size == 0>
			                		<tr>
			                			<td colspan="8" class="text-center">无可用代理商
			                				[<a href="${request.contextPath}/admin/agent/add">新增代理商</a>]
			                			</td>
			                		</tr>
			                	</#if>
			                	<#list acList as ac>
				                	<tr>
				                		<td>
				                			<input type="radio" name="agentId" value="${ac.agent.id}"/>
				                		</td>
				                		<td>${ac?counter}</td>
				                		<td>${ac.agent.name}</td>
				                		<td>${ac.agent.contact}</td>
				                		<td>${ac.agent.phone}</td>
				                		<td>${ac.deviceList?size}</td>
				                		<td>${ac.createTime}</td>
				                		<td>
				                			<#if ac.agent.status == 0>禁用
										 	<#elseif ac.agent.status == 1>启用
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
	<script src="${request.contextPath}/js/checkbox.js"></script>
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
	
	function conf(id) {
		$.ajax({
			url: '${request.contextPath}/admin/device/confAction/' + id,
			dataType: 'json',
			success: function(data) {
				alert('提交成功');
				location.reload();
			}
		});
	}
	
	function batchConf() {
		var ids = checkedValues('ids');
		if(ids) {
			$.ajax({
				url: '${request.contextPath}/admin/device/confAction',
				data: 'ids=' + ids,
				dataType: 'json',
				type: 'POST',
				success: function(data) {
					alert('提交成功');
					location.reload();
				}
			});
		}
	}
	
	var deviceId = null;
	function openModal(id) {
		deviceId = id;
		$('#agentSelectionModal').modal();
	}
	
	function assign() {
		var agentId = $(':checked[name="agentId"]').val();
		if(agentId) {
			$.ajax({
				url: '${request.contextPath}/admin/device/assignAction',
				data: 'agentId=' + agentId + '&deviceId=' + deviceId,
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