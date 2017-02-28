<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/themes/default/easyui.css" />

<link
	href="${pageContext.request.contextPath }/resources/css/extEasyUIIcon.css"
	rel="stylesheet" media="screen" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/themes/icon.css" />	
<link href="${pageContext.request.contextPath }/resources/css/admin.css" rel="stylesheet" media="screen"  />

</head>
<body class="easyui-layout">

	<div data-options="region:'north',title:'header',split:true,noheader:true" style="height:60px;background:#2d3e50;">
		
		<div class="admin_logo">后台管理</div>
		<div class="logout">
			您好！${user} | <a href="logout">退出</a>
		</div>
	</div>
	
<div data-options="region:'south',title:'footer',split:true,noheader:true" style="height:35px;line-height:30px;text-align:center;">
	&copy;2015-2016 Powered by JAVA and EasyUI.
</div>    
<div data-options="region:'west',title:'功能菜单',split:true" style="width:180px;padding:10px;">
	<ul id="nav"></ul>
</div>
<!-- <div id="mm" class="easyui-menu" >
	<div data-options="iconCls:'icon-add'">新增菜单</div>
	<div data-options="iconCls:'icon-edit'">修改菜单</div>
	<div data-options="iconCls:'icon-remove'">删除菜单</div>
</div> -->






   
<div data-options="region:'center'" style="overflow:hidden;">
	<div id="tabs">
		<div title="首页" style="padding:0 10px;display:block;">
		
			<div style="margin:10px 20px ;text-align: center;">
				<h1>欢迎使用后台管理系统 </h1>
			</div>
			<div style="border: 1px solid #eee;text-align: center;">
				<form id="site_form">
				<input type="hidden" name="siteId" value="${site.siteId }"/>
				<div style="margin:10px 20px">
					<p><label class="_site_label">站点名称：</label><input class="_site" type="text" name="siteName" value="${site.siteName }"/> </p>
				</div>
				<div style="margin:10px 20px">
					<p><label class="_site_label">站点地址：</label><input class="_site" type="text" name="siteUrl" value="${site.siteUrl }" /> </p>
				</div>
				<div style="margin:10px 20px">
					<p><label class="_site_label">站点LOGO：</label><input class="_site"  name="file"  /> </p>
				</div>
				<div style="margin:10px 20px">
					<p><label class="_site_label">站点关键字：</label><input class="_site" type="text" name="siteKeyword" value="${site.siteKeyword }"/></p>
				</div>
				<div style="margin:10px 20px">
					<p><label class="_site_label">站点描述：</label> <textarea class="_site2" name="siteDescription"> ${site.siteDescription }</textarea> </p>
				</div>
				<div style="margin:10px 20px">
					<p><label class="_site_label">站点版权：</label><input class="_site" type="text" name="siteCopyright" value="${site.siteCopyright }" /> </p>
				</div>
				</form>
				<div style="margin:10px 20px">
					<p style="margin-left: 200px;"><a id="site_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">修改</a></p>
				</div>
			</div>
		</div>
	</div>
</div> 
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/script/admin.js"></script>

</body>
</html>