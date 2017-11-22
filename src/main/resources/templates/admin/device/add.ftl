<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
<style type="text/css">
em {color: red;}
</style>
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h1 class="page-header">新增设备</h1>
			<!-- form -->
			<form id="addDeviceForm" name="addDeviceForm" class="form-horizontal" role="form"
					action="${request.contextPath}/admin/device/addAction" method="post">
            	
            	<!-- applyId -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3">批次</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="applyId" name="applyId" placeholder="请输入批次"/>
	                </div>
            	</div>
            	
            	<!-- deviceId -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3">设备ID</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="deviceId" name="deviceId" placeholder="请输入设备ID"/>
	                </div>
            	</div>
            	<!-- uuid -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3">设备UUID</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="uuid" name="uuid" placeholder="请输入设备UUID"/>
	                </div>
            	</div>
            	<!-- major -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3"><em>*&nbsp;</em>设备major值(10进制)</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="major" name="major" 
	                     	placeholder="请输入设备major值(10进制)" required/>
	                </div>
            	</div>
                <!-- minor -->
                <div class="form-group">
	                <label class="control-label col-xs-3"><em>*&nbsp;</em>设备minor值(10进制)</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="minor" name="minor" 
	                     	placeholder="请输入设备minor值(10进制)" required/>
	                </div>
                </div>
                <!-- comment -->
                <div class="form-group">
                	<label class="control-label col-xs-3">备注信息</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="comment" name="comment" placeholder="请输入备注信息"/>
	                </div>
                </div>
                <!-- poiId -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3">门店ID</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="poiId" name="poiId" placeholder="请输入门店ID"/>
	                </div>
            	</div>
                <div class="form-group text-center">
                     <input type="submit" class="btn btn-info" value="提交"/>
                </div>
            </form>
		</div>
	</div>
	
	<script src="${request.contextPath}/js/jquery-3.2.0.min.js"></script>
	<script src="${request.contextPath}/js/jquery.form.js"></script>
	<script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
	$(function() {
		$('#addDeviceForm').ajaxForm({
			dataType: 'json',
		    success: function() {
		    	alert('提交成功');
		    	location = '${request.contextPath}/admin/device';
		    }
		});
	});
	</script>
</body>
</html>