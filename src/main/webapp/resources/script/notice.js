$(function() {

	$("#notice").datagrid({

		//url:'/seckill/test/getnotice',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 10,
		pageList : [10, 20, 30, 40, 50],
		pageNumber : 1,
		toolbar : '#notice_tool',
		columns:[[
		           {
		        	   field:'id',
		        	   title:'自动编号',
		        	   width:100,
		        	   checkbox:true
		        	},{
		        		field:'title',
		        		title:'标题',
		        		width:100
		        	},{
						field : 'content',
						title : '内容信息',
						width : 300,
					},{
						field : 'date',
						title : '创建日期',
						width : 100,
					},
		        		
		 ]]
	});
	
	
	$("#notice_add").dialog({
		title:'新增消息',
		width:350,
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			handler:function(){
				$.ajax({
					url:'/seckill/test/getnotice',
					type:'post',
					data:{
						title:$("input[name='title']").val(),
						content:$('textarea[name="content"]').val()
					},
					beforeSend:function(){
						$.messager.progress({
							text:'正在加载数据信息'
						});
					},
					success:function(data){
						$.messager.progress('close');
						
						$("#notice_add").dialog("close");
						
						$.messager.show({
							title:'提示',
							msg:'新增成功'
						});
						
					}
					
				});
			}
		},{
			text:'取消',
			handler:function(){
				$("#notice_add").dialog('close');
			}
		}],
	});
	
	
	
	notice_tool={
			add:function(){
				$("#notice_add").dialog('open');
			}
	}
});