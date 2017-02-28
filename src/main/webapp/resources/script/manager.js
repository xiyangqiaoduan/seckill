$(function(){
	var path="/seckill";
	$("#manager").datagrid({
		url:path+'/test/manager_data',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 10,
		pageList : [10, 20, 30, 40, 50],
		pageNumber : 1,
		toolbar : '#manager_tool',
		columns:[[
		           {
		        	   field:'id',
		        	   title:'自动编号',
		        	   width:100,
		        	   checkbox:true
		        	},{
		        		field:'user',
		        		title:'管理员账号',
		        		width:100
		        	},{
						field : 'auth',
						title : '拥有权限',
						width : 100,
					},{
						field : 'date',
						title : '创建日期',
						width : 100,
					},
		        		
		 ]]
		
	});

	
	
	$("#manager_add").dialog({
		title:'新增用户管理',
		modal:true,
		closed : true,
		iconCls : 'icon-user-add',
		width:350,
		buttons:[{
			text:'提交',
			iconCls:'icon-add-new',
			handler:function(){
				if($('#manager_add').form('validate')){
				
					$.ajax({
						url:path+'/test/add_manager',
						type:'post',
						data:{
							user:$('input[name="manager"]').val(),
							pwd:$('input[name="password"]').val(),
							//authId:$('#auth').combotree('getValue'),//获取ID
							authName:$("#auth").combotree('getText')//获取名称
						},
						beforeSend:function(){
							$.messager.progress({
								text:'正在新增用户......'
							});
						},
						success:function(data, response, status){
							$.messager.progress('close');
							if(data.success){
								$.messager.show({
									title:'提示',
									msg:data.msg
								});
								
								$("#manager_add").dialog('close').form('reset');
								$("#manager").datagrid("reload");
								
							}else{
								$.messager.alert('新增失败！', '未知错误导致失败，请重试！', 'warning');
							}
						}
						
					});
					
					
				}
			}
		},{
			text:'取消',
			iconCls:'icon-redo',
			handler:function(){
				$("#manager_add").dialog('close').form('reset');
			}
		}]
		
		
	});
	
	
	$("#manager_edit").dialog({
		title:'修改账户信息',
		closed:true,
		width:350,
		modal:true,
		iconCls:'icon-edit-user',
		buttons:[{
			text:'提交',
			iconCls : 'icon-edit-new',
			handler:function(){
				$("#manager_edit").dialog('close').form('reset');
				$("#manager").datagrid("reload");
				
			}
		},{
			text:'取消',
			iconCls:'icon-redo',
			handler:function(){
				$("#manager_edit").dialog('close').form('reset');
			}
		}]
	});
	
	
	
	$('input[name="manager"]').validatebox({
		required:true,
		validType:'length[2,20]',
		missingMessage:'请输入管理员账号信息',
		invalidMessage : '管理名称在 2-20 位',
	});
	//管理密码
	$('input[name="password"]').validatebox({
		required : true,
		validType : 'length[6,30]',
		missingMessage : '请输入管理密码',
		invalidMessage : '管理密码在 6-30 位',
	
	});
	
	$("#auth").combotree({
		url:path+"/test/nav",
		required:true,
		lines:true,
		multiple : true,
		checkbox:true,
		onlyLeafCheck : true,
		onLoadSuccess : function (node, data) {
			var _this = this;
			if (data) {
				$(data).each(function (index, value) {
					if (this.state == 'closed') {
						$(_this).tree('expandAll');
					}
				});
			}
		},
	});
	
	manager_tool={
			
			reload:function(){
				$("#manager").datagrid("reload");
			},
			redo:function(){
				$("#manager").datagrid("unselectAll");
			},
			
			remove:function(){
				
				var rows=$("#manager").datagrid("getSelections");
				console.log(rows);
				if(rows.length>0){
					
					$.messager.confirm("确定操作","您正要删除所选的记录吗？",function(flag){
						
						if(flag){
							var ids=[];
							
							for(var i=0;i<rows.length;i++){
								ids.push(rows[i].id);
							}
							
							$.ajax({
								url:path+"/test/deleteManager",
								data:{
									ids:ids,
								},
								type:'post',
								
								beforeSend:function(){
									$('#manager').datagrid('loading');
								},
								success:function(data){
									
									if(data){
										$("#manager").datagrid("loaded");
										$('#manager').datagrid('load');
										$('#manager').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : data.num + '个管理被删除成功！',
										});
										
									}
									
									
								}
								
							});
							
							
						}
					});
					
					
					
				}else{
					$.messager.alert("提示","请选择删除的信息",'info');
				}
				
				
			},
			
			add:function(){
				$("#manager_add").dialog("open");
			},
			edit:function(){
				var rows = $('#manager').datagrid('getSelections');
	
				if(rows.length==0){
					$.messager.alert("警告",'请选择一条您要编辑内容数据！','warning');
				}else if(rows.length>1){
					$.messager.alert("警告",'编辑记录只能选择一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						url:path+'/test/getmanager',
						type:'post',
						data:{
							id:rows[0].id
						},
						beforeSend:function(){
							$.messager.progress({
								title:'正在获取数据,请稍后。。。。。。',
							});
						},
						success:function(data,response,status){
							
							$.messager.progress('close');
							if(data){
								var auth=data.auth.split(",");
								
								$("#manager_edit").form('load',{
									id:data.id,
									manager_edit:data.user,
								}).dialog('open');
							
								$("#auth_edit").combotree({
									url:path+"/test/nav",
									required : true,
									lines : true,
									multiple : true,
									checkbox : true,
									onlyLeafCheck : true,
									onLoadSuccess:function(node,data){
										var _this = this;
										if (data) {
											$(data).each(function (index, value) {
												
												if ($.inArray(value.text, auth) != -1) {
													$(_this).tree('check', value.target);
												}
												if (this.state == 'closed') {
													$(_this).tree('expandAll');
												}
											});
										}
										
									}
								});
								
								
							}
							
						}
						
					});
					
					
				}else{
					$.messager.alert("警告",'未知错误信息，请重试！','warning');
				}
			
			}
	}
	
	
	
});