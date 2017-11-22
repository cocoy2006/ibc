<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8"/>
		<meta name="viewport" content="width=device-width,initial-scale=1"/>
		<link href="${request.contextPath}/jquery-weui/dist/lib/weui.css" rel="stylesheet"/>
    	<link href="${request.contextPath}/jquery-weui/dist/css/jquery-weui.css" rel="stylesheet"/>
		<title>摇一摇</title>
	</head>
	<body>
		<div class="row" style="background-color: #5A9BD5; height: 140px; padding-left: 20px;">
      		<h3>${packet.title}</h3>
   			<h6>${packet.description}</h6>
      	</div>
      	<div>
   			<img src="${request.contextPath}/upload/${packet.picUrl}" class="center-block" style="max-width: 100%;"/>
   		</div>
   		<div>
   			<a href="${request.contextPath}/packet?${params}">
				<img src="${request.contextPath}/image/hongbao.jpg" style="width: 100%;"/>
			</a>
   		</div>
		
		<script src="${request.contextPath}/jquery-weui/dist/lib/jquery-2.1.4.js" type="text/javascript"></script>
		<script src="${request.contextPath}/jquery-weui/dist/js/jquery-weui.js"></script>
		<script src="http://zb.weixin.qq.com/nearbycgi/addcontact/BeaconAddContactJsBridge.js"></script>
		<script>
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
		</script>
	</body>
</html>