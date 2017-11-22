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
			<h1 class="page-header">充值中心</h1>
			<!-- form -->
			<form id="rechargeForm" name="rechargeForm" class="form-horizontal" role="form"
					action="${request.contextPath}/owner/rechargeAction" method="post">
					
            	<!-- amount total -->
            	<div class="form-group">
	            	<label class="control-label col-xs-2">我的余额</label>
	                <div class="col-xs-10">
	                	<p class="form-control-static">${Session.owner.amountTotal!"--"}元</p>
	                </div>
            	</div>
            	
            	<!-- amount recharge -->
            	<div class="form-group">
	            	<label class="control-label col-xs-2">充值金额</label>
	                <div class="col-xs-10">
						<input type="number" class="form-control" id="amountRecharge" name="amountRecharge" 
							onkeyup="payment(this)" onblur="payment(this)"
							placeholder="请输入充值金额" required/>
						<span class="help-block">注意事项：每笔充值需收取${fee * 100}%的手续费，以实际支付金额为准</span>
					</div>
            	</div>
            	
            	<!-- amount payment -->
            	<div class="form-group">
	            	<label class="control-label col-xs-2">实际支付金额</label>
	                <div class="col-xs-10">
	                     <input type="number" class="form-control" id="amountPayment" 
	                     	name="amountPayment" readonly="readonly" value="" />
	                </div>
            	</div>
            	
                <div class="form-group text-center">
                     <input type="submit" class="btn btn-info" value="充值"/>
                </div>
            </form>
		</div>
	</div>
	
	<script src="${request.contextPath}/js/jquery-3.2.0.min.js"></script>
	<script src="${request.contextPath}/js/jquery.form.js"></script>
	<script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
	$(function() {
		$('#rechargeForm').ajaxForm({
			dataType: 'json',
		    success: function() {
		    	alert('充值成功');
		    	location = '${request.contextPath}/owner/recharge';
		    }
		});
	});
	
	function payment(that) {
		var p = new Number(that.value) / (1 - new Number(${fee}));
		$('#amountPayment').val(p.toFixed(2));
	}
	</script>
</body>
</html>