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
			<h1 class="page-header">红包列表
				<a class="btn btn-info pull-right" 
					href="${request.contextPath}/owner/packet/add">新增红包</a>
			</h1>
			<table class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                     <tr>
                          <th>序号</th>
                          <th>活动主题</th>
                          <th>活动描述</th>
                          <th>红包金额</th>
                          <th>红包数量</th>
                          <th>类型</th>
                          <th>状态</th>
                          <th>操作</th>
                     </tr>
                </thead>
                <tbody>
                	<#if packetList?size == 0>
                		<tr>
                			<td colspan="8" class="text-center">无可用红包
                				[<a href="${request.contextPath}/owner/packet/add">新增红包</a>]
                			</td>
                		</tr>
                	</#if>
                	<#list packetList as packet>
	                	<tr>
						    <td>${packet?counter}</td>
	                		<td>${packet.title}</td>
	                		<td>${packet.description}</td>
	                		<td>${packet.amountLeft}/${packet.amountTotal}</td>
	                		<td>${packet.numLeft}/${packet.numTotal}</td>
	                		<td>
	                			<#if packet.type == 0>未知
							 	<#elseif packet.type == 1>普通红包
							 	<#elseif packet.type == 2>拼手气红包
							 	</#if>
	                		</td>
	                		<td>
	                			<#if packet.status == 0>已结束
							 	<#elseif packet.status == 1>未绑定
							 	<#elseif packet.status == 2>活动进行中
							 	</#if>
	                		</td>
	                		<td>
	                			<div class="btn-group">
								  	<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									更多 <span class="caret"></span>
								  	</button>
									<ul class="dropdown-menu dropdown-menu-right">
									  	<li><a href="${request.contextPath}/owner/packet/${packet.id}">查看</a></li>
									  	<#if packet.status == 1>
									  		<li><a href="#" onclick="openModal(${packet.id})">绑定设备</a></li>
									  		<li><a href="#">编辑</a></li>
									  	</#if>
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
	
	<!-- 设备选择的模态框 --> 
    <div id="deviceSelectionModal" class="modal fade" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
              <div class="modal-content">
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">请选择设备</h4>
                   </div>
                   <div class="modal-body">
                        <table class="table table-striped table-bordered" cellspacing="0" width="100%">
			                <thead>
			                    <tr>
			                    	<th>
			                    		<input type="checkbox" id="checkboxAll"
						            		onclick="checkAll(this, 'deviceIds')">
			                    	</th>
			                       	<th>major</th>
			                       	<th>minor</th>
			                       	<th>备注</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<#if deviceList?size == 0>
			                		<tr>
			                			<td colspan="5" class="text-center">无可用设备
			                				[<a href="${request.contextPath}/owner/device">管理设备</a>]
			                			</td>
			                		</tr>
			                	</#if>
			                	<#list deviceList as device>
				                	<tr>
				                		<td><input type="checkbox" name="deviceIds" value="${device.id}"
				                				onclick="cancelCheckAll('checkboxAll', 'deviceIds')"/></td>
				                		<td>${device.major}</td>
				                		<td>${device.minor}</td>
				                		<td>${device.comment}</td>
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
	
	var packetId = null;
	function openModal(id) {
		packetId = id;
		$('#deviceSelectionModal').modal();
	}
	
	function bind() {
		var deviceIds = checkedValues('deviceIds');
		if(deviceIds) {
			$.ajax({
				url: '${request.contextPath}/owner/bind',
				data: 'packetId=' + packetId + '&deviceIds=' + deviceIds,
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