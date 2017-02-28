<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理首页</title>

<%@ include file="../common/inc.jsp"%>

<script type="text/javascript">

	var index_layout;
	$(function(){
		index_layout=$('#index_layout').layout({
			fit:true
		});
	});

</script>

</head>
<body>
	<div id="index_layout" >
		<div data-options="region:'north',href:'${pageContext.request.contextPath}/manage/north'" style="height: 70px; overflow: hidden;" class="logo">
		
		</div>
		 <div data-options="region:'west',split:true,href:'${pageContext.request.contextPath}/manage/west'" title="模块导航" style="width: 200px; overflow: hidden;"></div>
		<div data-options="region:'center'" title="欢迎使用EasyUI" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false"
					style="overflow: hidden;">
					<iframe src="${pageContext.request.contextPath}/manage/center" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
				</div>
			</div>
		</div>
		<!--
		<div data-options="region:'east'" title="日历" style="width: 230px; overflow: hidden;"></div>
		<div data-options="region:'south',border:false" style="height: 30px; overflow: hidden;"></div> -->
	</div>

</body>
</html>