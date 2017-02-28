<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/tag.jsp"%>



<div id="sessionInfoDiv" style="position: absolute; right: 0px; top: 0px;" class="alert alert-info">
	[<strong>admin</strong>]，欢迎你！您使用[<strong>127.0.0.1</strong>]IP登录！
</div>
<div style="position: absolute; right: 10px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'house'">更换皮肤</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">控制面板</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'user'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeThemeFun('default');" title="default">default</div>
	<div onclick="changeThemeFun('gray');" title="gray">gray</div>
	<div onclick="changeThemeFun('metro');" title="metro">metro</div>
	<div onclick="changeThemeFun('bootstrap');" title="bootstrap">bootstrap</div>
	<div onclick="changeThemeFun('black');" title="black">black</div>
	<div class="menu-sep"></div>
	<div onclick="changeThemeFun('cupertino');" title="cupertino">cupertino</div>
	<div onclick="changeThemeFun('dark-hive');" title="dark-hive">dark-hive</div>
	<div onclick="changeThemeFun('pepper-grinder');" title="pepper-grinder">pepper-grinder</div>
	<div onclick="changeThemeFun('sunny');" title="sunny">sunny</div>
	<div class="menu-sep"></div>
	<div onclick="changeThemeFun('metro-blue');" title="metro-blue">metro-blue</div>
	<div onclick="changeThemeFun('metro-gray');" title="metro-gray">metro-gray</div>
	<div onclick="changeThemeFun('metro-green');" title="metro-green">metro-green</div>
	<div onclick="changeThemeFun('metro-orange');" title="metro-orange">metro-orange</div>
	<div onclick="changeThemeFun('metro-red');" title="metro-red">metro-red</div>
</div>

<div id="layout_north_kzmbMenu" style="width: 100px;display: none;">
	<div>修改密码</div>
	<div class="menu-sep"></div>
	<div>我的角色</div>
	<div class="menu-sep"></div>
	<div>我的权限</div>
</div>
<div id="layout_north_zxMenu" style="width:100px;display: none;">
	<div>锁定窗口</div>
	<div class="menu-sep"></div>
	<div>重新登录</div>
	<div>退出系统</div>
</div>
