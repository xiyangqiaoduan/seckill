<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	/* .datagrid-cell-c1-date .datagrid-sort-icon{
		padding: 0 6px 0 0;
	}
 */

</style>
<table id="manager"></table>

<div id="manager_tool" style="padding:5px;">

	<div style="margin-bottom: 5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="save" onclick="manager_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" id="redo" onclick="manager_tool.redo();">取消选择</a>		
	</div>
	
	<div style="padding:0 0 0 7px;color:#333;">
		查询帐号：<input type="text" class="textbox" name="user" style="width:110px">
		创建时间从：<input type="text" name="date_from" class="easyui-datebox" editable="false" style="width:110px">
		到：<input type="text" name="date_to" class="easyui-datebox" editable="false" style="width:110px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
	</div>
</div>

<form id="manager_add">
	<p>管理账号：<input type="text" name="manager" class="textbox" style="width:200px;"/></p>
	<p>管理密码：<input type="password" name="password" class="textbox" style="width:200px;"></p>
	<p>分配权限：<input id="auth" class="textbox" name="auth" style="width:205px;"></p>
</form>

<form id="manager_edit">
	<input type="hidden" name="id" class="textbox" style="width:200px;"/>
	<p>管理账号：<input type="text" name="manager_edit" class="textbox" disabled="disabled" style="width:200px;" /></p>
	<p>管理密码：<input type="password" name="password_edit" class="textbox" disabled="disabled" style="width:200px;" /></p>
	<p>分配权限：<input id="auth_edit" class="textbox" name="auth_edit" style="width:205px;"></p>
</form>


    	
 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/manager.js"></script>
    