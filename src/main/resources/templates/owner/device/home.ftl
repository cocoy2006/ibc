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
	                			<#if dc.device.status == 4>未绑定活动
							 	<#elseif dc.device.status == 5>正在使用
							 	<#elseif dc.device.status == 9>已删除
							 	</#if>
	                		</td>
	                		<td>
	                			<div class="btn-group">
								  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    更多 <span class="caret"></span>
								  </button>
								  <ul class="dropdown-menu dropdown-menu-right">
								  	<#if dc.device.status == 4>
								  		<li><a href="#" onclick="openModal(${dc.device.id})">绑定红包</a></li>
								  		<li><a href="#">删除</a></li>
								  	<#elseif dc.device.status == 5>
								  		<li><a href="#" onclick="unbound(${dc.device.id})">解绑红包</a></li>
								  	</#if>
								  </ul>
								</div>
	                		</td>
	                	</tr>
                	</#list>
                </tbody>
           </table>
		</div>
	</div>
	
	<!-- 红包选择的模态框 --> 
    <div id="packetSelectionModal" class="modal fade" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
              <div class="modal-content">
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">请选择红包</h4>
                   </div>
                   <div class="modal-body">
                        <table class="table table-striped table-bordered" cellspacing="0" width="100%">
			                <thead>
			                    <tr>
			                    	<th></th>
			                       	<th>活动主题</th>
			                        <th>活动描述</th>
			                        <th>类型</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<#if packetList?size == 0>
			                		<tr>
			                			<td colspan="8" class="text-center">无可用红包
			                				[<a href="${request.contextPath}/owner/packet">管理红包</a>]
			                			</td>
			                		</tr>
			                	</#if>
			                	<#list packetList as packet>
				                	<tr>
				                		<td><input type="radio" name="packetId" value="${packet.id}"/></td>
				                		<td>${packet.title}</td>
				                		<td>${packet.description}</td>
				                		<td>
				                			<#if packet.type == 0>未知
										 	<#elseif packet.type == 1>普通红包
										 	<#elseif packet.type == 2>拼手气红包
										 	</#if>
				                		</td>
				                	</tr>
			                	</#list>
			                </tbody>
			           </table>
                   </div>
                   <div class="modal-footer">
                        <button type="button" class="btn btn-info" data-dismiss="modal" onclick="bind()">提交绑定</button>
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
		$('#packetSelectionModal').modal();
	}
	
	function bind() {
		var packetId = $(':checked[name="packetId"]').val();
		if(packetId) {
			$.ajax({
				url: '${request.contextPath}/owner/bind',
				data: 'packetId=' + packetId + '&deviceIds=' + deviceId,
				dataType: 'json',
				type: 'POST',
				success: function(data) {
					alert('提交成功');
					location.reload();
				}
			});
		}
	}
	
	function unbound(id) {
		$.ajax({
			url: '${request.contextPath}/owner/unbound',
			data: 'deviceId=' + id,
			dataType: 'json',
			type: 'POST',
			success: function(data) {
				alert('提交成功');
				location.reload();
			}
		});
	}
	
	function del(id) {
		
	}

	</script>
</body>
</html>