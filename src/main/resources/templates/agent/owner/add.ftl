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
			<h1 class="page-header">新增商家</h1>
			<!-- form -->
			<form id="addOwnerForm" name="addOwnerForm" class="form-horizontal" role="form"
					action="${request.contextPath}/agent/owner/addAction" method="post">
            	
            	<!-- name -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3"><em>*&nbsp;</em>企业名称</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="name" name="name" 
	                     	placeholder="请输入代理商名称，最多12个字" required/>
	                </div>
            	</div>
            	<!-- username -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3"><em>*&nbsp;</em>登录账号</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="username" name="username" 
	                     	placeholder="请输入用户名" required/>
	                </div>
            	</div>
            	<!-- password -->
            	<div class="form-group">
	            	<label class="control-label col-xs-3"><em>*&nbsp;</em>登录密码</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="password" name="password" 
	                     	placeholder="请输入登录密码" required/>
	                </div>
            	</div>
                <!-- contact -->
                <div class="form-group">
	                <label class="control-label col-xs-3"><em>*&nbsp;</em>联系人</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="contact" name="contact" 
	                     	placeholder="请输入联系人，最多12个字" required/>
	                </div>
                </div>
                <!-- phone -->
                <div class="form-group">
                	<label class="control-label col-xs-3"><em>*&nbsp;</em>联系电话</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control" id="phone" name="phone" 
	                     	placeholder="请输入联系电话" required/>
	                </div>
                </div>
                <!-- expire time -->
                <div class="form-group">
                	<label class="control-label col-xs-3"><em>*&nbsp;</em>过期时间</label>
	                <div class="col-xs-9">
	                     <input type="text" class="form-control datetimepicker" id="expireTime" name="expireTime" 
	                     	placeholder="请输入过期时间" required/>
	                </div>
                </div>
                <!-- sub industry -->
                <div class="form-group">
                	<label class="control-label col-xs-3"><em>*&nbsp;</em>所属行业</label>
	                <div class="col-xs-4">
	                     <select id="industryId" name="industryId" class="form-control"
	                     	onchange="fillSubIndustry(this)">
	                     	<option value="">请选择</option>
	                     	<#list industryList as industry>
	                     		<option value="${industry.id}">${industry.name}</option>
	                     	</#list>
	                     </select>
	                </div>
	                <div class="col-xs-5">
	                     <select id="subIndustryId" name="subIndustryId" class="form-control">
	                     	<!-- fill options via ajax -->
	                     </select>
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
	$(".datetimepicker").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 'month'
	});
	
	$(function() {
		$('#addOwnerForm').ajaxForm({
			dataType: 'json',
		    success: function() {
		    	alert('提交成功');
		    	location = '${request.contextPath}/agent/owner';
		    }
		});
	});
	
	function fillSubIndustry(that) {
		var industryId = $(that).val();
		if(industryId) {
			$.ajax({
				url: '${request.contextPath}/subIndustry/' + industryId,
				dataType: 'json',
				success: function(data) {
					var options = '<option value="">请选择</option>';
					$.each(data, function(index, item) {
						options += '<option value="' + item.id + '">' + item.name + '</option>';
					});
					$('#subIndustryId').html(options);
				}
			});
		}
	}
	</script>
</body>
</html>