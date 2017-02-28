<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理系统登录</title>

<!-- 引入bootstrap样式 -->
<%-- <link
	href="${pageContext.request.contextPath }/resources/bootstrap-2.3.1/css/bootstrap.min.css"
	rel="stylesheet" media="screen" /> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/themes/default/easyui.css" />

<link
	href="${pageContext.request.contextPath }/resources/css/extEasyUIIcon.css"
	rel="stylesheet" media="screen" />
<link href="${pageContext.request.contextPath }/resources/css/login.css" rel="stylesheet" media="screen"  />
</head>
<body>

	<div id="login">
		<p>管理员帐号：<input type="text" id="manager" class="textbox"></p>
		<p>管理员密码：<input type="password" id="password" class="textbox"></p>
	</div>

	<div id="btn">
		<a href="#" class="easyui-linkbutton">登录</a>
	</div>
	



	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/index.js"></script>


</body>
</html>