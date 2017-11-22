<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
<link href="${request.contextPath}/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<style type="text/css">
em {color: red;}
</style>
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h1 class="page-header">申请设备</h1>
			<!-- form -->
			<form id="applyForm" name="applyForm" class="form-horizontal" role="form"
					action="${request.contextPath}/admin/device/applyAction" method="post">
            	
            	<!-- quantity -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3"><em>*&nbsp;</em>申请数量</label>
	                <div class="col-xs-9">
	                     <input type="number" class="form-control" id="quantity" name="quantity" 
	                     	placeholder="请输入申请数量，只能是数字" max="50" min="1" required/>
	                </div>
            	</div>
            	<!-- reason -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3"><em>*&nbsp;</em>申请理由</label>
	                <div class="col-xs-9">
	                     <textarea class="form-control" id="reason" name="reason" 
	                     	placeholder="请输入申请理由，不能为空且长度不能超过100个汉字或200个字母" required></textarea>
	                </div>
            	</div>
            	<!-- comment -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3"><em>*&nbsp;</em>备注</label>
	                <div class="col-xs-9">
	                     <textarea class="form-control" id="comment" name="comment" 
	                     	placeholder="请输入备注，不能为空且长度不能超过15个汉字或30个字母" required></textarea>
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
	<script src="${request.contextPath}/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${request.contextPath}/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script>
	$(function() {
		$('#applyForm').ajaxForm({
			dataType: 'json',
		    success: function() {
		    	alert('提交成功');
		    	location = '${request.contextPath}/admin/device/history';
		    }
		});
	});
	</script>
</body>
</html>