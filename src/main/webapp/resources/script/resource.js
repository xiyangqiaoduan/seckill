$(function() {
	var treegrid;
	var path="/seckill";
	treegrid=$("#resource").treegrid({

		url : path+'/manage/resource/treeGrid',
		idField : 'id',
		treeField : 'name',
		parentField:'pid',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border:false,
		
		toolbar:'#toolbar',
		
		columns : [ [ {
			title : '编号',
			field : 'id',
    		width:100,
			hidden:true
		}, {
			field : 'name',
			title : '资源名称',
			width:100,
		}, {
			field : 'url',
			title : '资源路径',
			width : 200,
		}, {
			field : 'typeId',
			title : '资源类型ID',
			width:100,
			hidden:true
		} ,{
			field : 'typeName',
			title : '资源类型',
			width:100,
		},{
			field : 'seq',
			title : '排序',
			width:100,
		},{
			field:'pid',
			title:'上级资源ID',
			hidden:true,
			width:100,
		},{
			field:'pname',
			title:'上级资源',
			width:100,
		},{
			field:'action',
			title:'操作',
			width:100,
			formatter:function(value,row,index){
			
				var str='';
				if(row.pid!='0'){
					
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, path+'/resources/css/images/extjs_icons/pencil.png');
					str += '&nbsp;';
					str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, path+'/resources/css/images/extjs_icons/cancel.png');
				}
				return str;
			}
		}, {
			field : 'remark',
			title : '备注',
			width : 150
		} ] ],
		onContextMenu: function(e, row) {
			e.preventDefault();
			$(this).treegrid('unselectAll');
			$(this).treegrid('select', row.id);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}

	});

	 $.formatString=function(str){
			for ( var i = 0; i < arguments.length - 1; i++) {
				str = str.replace("{" + i + "}", arguments[i + 1]);
			}
			return str;
		 
	 }
	 
	 resoure_tool={
		 addFun:function(){
			 var node=treegrid.treegrid("getSelected");
			 if(node){
				 
				 var pid=node.id;
				 
				 $("#resource_dialog").dialog({
					 title:'新增资源',
					 width:500,
					 height:300,
					 modal:true,
					 href:path+"/manage/resource/add"
				 });
			 }
		 }
	 }
});