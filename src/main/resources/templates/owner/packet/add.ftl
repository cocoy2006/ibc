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
.preview {border: solid 1px #ccc; height: 500px;}
</style>
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h1 class="page-header">新增红包</h1>
			<!-- form -->
			<form id="addPacketForm" name="addPacketForm" class="form-horizontal col-xs-8" 
					enctype="multipart/form-data" role="form" 
					action="${request.contextPath}/owner/packet/addAction" method="post">
            	<!-- title -->
            	<div class="form-group">
	            	<label class="control-label col-xs-2"><em>*&nbsp;</em>活动主题</label>
	                <div class="col-xs-10">
	                     <input type="text" class="form-control" id="title" name="title" 
	                     	onkeyup="preview(this)" placeholder="请输入主题名称" required/>
	                </div>
            	</div>
            	
                <!-- description -->
                <div class="form-group">
	                <label class="control-label col-xs-2"><em>*&nbsp;</em>活动描述</label>
	                <div class="col-xs-10">
	                     <input type="text" class="form-control" id="description" name="description" 
	                     	onkeyup="preview(this)" placeholder="红包中奖领取页面描述" required/>
	                </div>
                </div>
                
                <!-- pic -->
                <div class="form-group">
	                <label class="control-label col-xs-2"><em>*&nbsp;</em>活动内容</label>
	                <div class="col-xs-10">
	                     <input type="file" class="form-control" id="pic" name="pic" required/>
	                </div>
                </div>
            	
                <!-- amount total -->
                <div class="form-group">
                	<label class="control-label col-xs-2"><em>*&nbsp;</em>红包总额</label>
	                <div class="col-xs-10">
	                     <input type="text" class="form-control" id="amountTotal" name="amountTotal" 
	                     	placeholder="请输入红包总额" required/>
	                </div>
                </div>
            	
                <!-- num total -->
                <div class="form-group">
	                <label class="control-label col-xs-2"><em>*&nbsp;</em>红包个数</label>
	                <div class="col-xs-10">
	                     <input type="text" class="form-control" id="numTotal" name="numTotal" 
	                     	placeholder="请输入红包个数" required/>
	                </div>
                </div>
            	
                <!-- type -->
                <div class="form-group">
                	<label class="control-label col-xs-2"><em>*&nbsp;</em>红包类型</label>
                	<div class="col-xs-10">
	                	<label class="radio-inline">
							<input type="radio" name="type" value="1" checked="checked"/> 普通红包
						</label>
						<label class="radio-inline">
						  	<input type="radio" name="type" value="2"/> 拼手气红包
						</label>
                	</div>
                </div>
            	
                <!-- start time & end time -->
                <div class="form-group">
	                <label class="control-label col-xs-2"><em>*&nbsp;</em>活动时间</label>
	                <div class="col-xs-10">
	                	<div class="col-xs-5">
	                		<input type="text" class="form-control datetimepicker" id="startTime" name="startTime" 
	                			placeholder="请输入开始时间" required/>
	                	</div>
	                	<div class="col-xs-2">至</div>
	                	<div class="col-xs-5">
	                		<input type="text" class="form-control datetimepicker" id="endTime" name="endTime" 
	                			placeholder="请输入结束时间" required/>
	                	</div>
	                </div>
                </div>
                <div class="form-group text-center">
                     <input type="submit" class="btn btn-info" value="提交"/>
                </div>
            </form>
            <!-- preview -->
            <div class="preview col-xs-4">
            	<div class="row" style="background-color: #5A9BD5; height: 140px; padding-left: 20px;">
            		<h3 id="title_preview">活动主题</h3>
           			<h6 id="description_preview">活动描述</h6>
            	</div>
            	<div>
           			<img src="..." class="img-thumbnail center-block" style="height: 150px; margin-top: 10px; width: 150px;"/>
           		</div>
           		<div>
           			<img src="${request.contextPath}/image/hongbao.jpg" style="bottom: 0; left: 0; width: 100%; position: absolute;"/>
           		</div>
            </div>
		</div>
	</div>
	
	<script src="${request.contextPath}/js/jquery-3.2.0.min.js"></script>
	<script src="${request.contextPath}/js/jquery.form.js"></script>
	<script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="${request.contextPath}/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${request.contextPath}/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script>
	$(".datetimepicker").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd hh:ii',
		language: 'zh-CN'
	});
	
	$(function() {
		$('#addPacketForm').ajaxForm({
			dataType: 'json',
		    success: function() {
		    	alert('提交成功');
		    	location = '${request.contextPath}/owner/packet';
		    }
		});
	});
	
	function preview(that) {
		var field = $(that).attr('name');
		var value = $(that).val();
		$('#' + field + '_preview').html(value);
	}
	</script>
</body>
</html>