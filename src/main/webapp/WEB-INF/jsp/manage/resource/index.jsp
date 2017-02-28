<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="resource"></table>

<div id="toolbar" style="display: none;">
	<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
	<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a>
	<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a>
	<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>

<div id="menu" class="easyui-menu" style="display:none">
	<div onclick="resoure_tool.addFun();" data-options="iconCls:'pencil_add'">增加</div>
	<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
	<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
</div>
<div id="resource_dialog"></div>


 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/resource.js"></script>
    