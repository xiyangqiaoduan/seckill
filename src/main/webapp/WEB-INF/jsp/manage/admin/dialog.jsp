<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/themes/icon.css" />
<script type="text/javascript">
	var path='${pageContext.request.contextPath}';

	$(function() {
		$("#mydialog").dialog({
			title : '我的dialog',
			width : 400,
			height : 200,
			modal:true,
			iconCls : 'icon-remove',
			resizable : false,//调整窗体大小
			collapsible : true,//可以折叠
			
			href:path+'/seckill/list',//动态加载网页内容信息
			
			toolbar : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					$("#mydialog").html("正在编辑信息。。。");
				}
			}, {
				text : '清空',
				iconCls : 'icon-clear',
				handler : function() {
					$("#mydialog").html("正在清空数据,请稍后。。。");
				}
			} ],
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					$("#mydialog").html("正在保存数据,请稍后。。。");
				}
			}, {
				text : '删除',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#mydialog").html("正在删除数据,请稍后。。。");
				}

			} ],
			onClose:function(){
				alert("关闭dialog");
				return false;
			},
			onLoad:function(){
				alert("正在记载着数据");
			}

		});
		
		
	});
</script>
</head>

<body>
	<!-- <div class="easyui-dialog" title="这是我的第一个dialog" style="width:400px;height:200px;" data-options="iconCls:'icon-save',resizable:true,modal:true">
			
	</div> -->

	<div id="mydialog"></div>

</body>
</html>