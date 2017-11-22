<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8"/>
		<meta name="viewport" content="width=device-width,initial-scale=1"/>
		<link href="${request.contextPath}/jquery-weui/dist/lib/weui.css" rel="stylesheet"/>
    	<link href="${request.contextPath}/jquery-weui/dist/css/jquery-weui.css" rel="stylesheet"/>
		<title>红包</title>
	</head>
	<body>
		<script src="${request.contextPath}/jquery-weui/dist/lib/jquery-2.1.4.js" type="text/javascript"></script>
		<script src="${request.contextPath}/jquery-weui/dist/js/jquery-weui.js"></script>
		<script>
		$.confirm("恭喜您获得了${amount}元", function() {
			history.back();
		}, function() {
			history.back();
		});
		</script>
	</body>
</html>