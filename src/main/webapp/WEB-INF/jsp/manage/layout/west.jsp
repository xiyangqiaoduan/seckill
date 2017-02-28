<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript">

var layout_west_tree;
var path='${pageContext.request.contextPath}';
var URL={
		tree:function(){
			return "${pageContext.request.contextPath}/manage/tree";
		}
}

var layout_west_tree_url = URL.tree();
$(function(){
	
	layout_west_tree=$("#layout_west_tree").tree({
		url:layout_west_tree_url,
		parentField : 'pid',
		
		onClick:function(node){
			if (node.attributes && node.attributes.url){
				var url=node.attributes.url;
				if(url.indexOf('/')==0){
					url=path+url;
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});	
				}
				addTab({
					url : url,
					title : node.text,
					iconCls : node.iconCls
				});
			}
		},
		
		onBeforeLoad:function(node,param){
			if(layout_west_tree_url){
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});				
			}
		},
		onLoadSuccess : function(node, data) {
			parent.$.messager.progress('close');
		}
	});
});


</script>	
	
<div class="easyui-accordion" data-options="fit:true,border:false">

	<div title="系统菜单" style="padding: 5px;"
		data-options="border:false,isonCls:'anchor',tools : [ {
				iconCls : 'database_refresh'
			}, {
				iconCls : 'resultset_next',
			}, {
				iconCls : 'resultset_previous',
			} ]">
		<div class="well well-small">
			<ul id="layout_west_tree"></ul>
		</div>
	</div>
	<div title="其他示例" data-options="border:false,iconCls:'anchor'">
		<ul>
			<li>菜单</li>
			<li>菜单</li>
			<li>菜单</li>
		</ul>
	</div>

</div>