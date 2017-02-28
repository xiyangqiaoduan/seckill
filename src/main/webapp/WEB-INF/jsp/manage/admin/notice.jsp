<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table id="notice"></table>

<div id="notice_tool">
	<div style="margin-bottom: 5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="notice_tool.add();">发布消息</a>
	</div>
</div>


<form id="notice_add">

	<p>标题：<input type="text" name="title" class="textbox" style="width:200px;"/></p>

	<p>内容：<textarea style="width:200px;height:150px;" name="content"></textarea></p>
	

</form>



  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/notice.js"></script>
   
    