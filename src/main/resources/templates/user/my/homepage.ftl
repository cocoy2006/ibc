<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8"/>
		<meta name="viewport" content="width=device-width,initial-scale=1"/>
		<link href="${request.contextPath}/jquery-weui/dist/lib/weui.css" rel="stylesheet"/>
    	<link href="${request.contextPath}/jquery-weui/dist/css/jquery-weui.css" rel="stylesheet"/>
		<title>我的主页</title>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="weui-cells">
				  <a class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      <p>钱包</p>
				    </div>
				    <div class="weui-cell__ft">说明文字</div>
				  </a>
				  <a class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      <p>卡包</p>
				    </div>
				    <div class="weui-cell__ft">说明文字</div>
				  </a>
				</div>
				
				<div class="weui-cells">
				  <a class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      <p>设置</p>
				    </div>
				    <div class="weui-cell__ft">说明文字</div>
				  </a>
				</div>
			</div>
		</div>
		
		<script src="${request.contextPath}/jquery-weui/dist/lib/jquery-2.1.4.js" type="text/javascript"></script>
		<script src="${request.contextPath}/jquery-weui/dist/js/jquery-weui.js"></script>
		<script src="http://zb.weixin.qq.com/nearbycgi/addcontact/BeaconAddContactJsBridge.js"></script>
		<script>
		/*
		BeaconAddContactJsBridge.ready(function(){
			//判断是否关注
			BeaconAddContactJsBridge.invoke('checkAddContactStatus',{} ,function(apiResult){
				if(apiResult.err_code == 0){
					var status = apiResult.data;
					if(status != 1){
						//跳转到关注页
					  	BeaconAddContactJsBridge.invoke('jumpAddContact');
					}
				} else {
					$.alert(apiResult.err_msg)
				}
			});
	 	});
		*/
		</script>
	</body>
</html>