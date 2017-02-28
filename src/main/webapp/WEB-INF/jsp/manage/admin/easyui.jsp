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
<!-- 引入bootstrap样式 -->
<link href="${pageContext.request.contextPath }/resources/bootstrap-2.3.1/css/bootstrap.min.css"
	rel="stylesheet" media="screen" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/script/jquery-easyui-1.4/themes/icon.css" />

<link href="${pageContext.request.contextPath }/resources/css/extEasyUIIcon.css" rel="stylesheet" media="screen" />	


<script type="text/javascript">
	var path='${pageContext.request.contextPath}';
	$(function(){
		
		
		$("#cc").layout({
			fit:true
		});
	
		$('#cc').layout('add',{
		    region: 'center',
		    title: '欢迎使用秒杀系统',
		    href:'/seckill/seckill/list',
		    border:false,	
		    tools: [{
		        iconCls:'icon-add',
		        handler:function(){
		        	
		        }
		    },{
		        iconCls:'icon-remove',
		        handler:function(){alert('remove')}
		    }]
		});
		
		$("#cc").layout("collapse","east");
		
		
		$('#user').textbox({
			required : true,
			missingMessage : '请输入您账号',
			delay:0
		});
		$('#pwd').textbox({
			required : true,
			missingMessage : '请输入您	密码',
			delay:0
		});
	
		
		
	});
	
	function my_dialog(){
		$("#my_dialog").dialog({
    		title:'添加用户信息',
    		modal:true,
    		width:400,
    		height:300,
    		buttons:[{
    			text:'保存',
    			iconCls:'icon-save',
    			handler:function(){
    				my_form();
    			}
    		},{
				text:'关闭',
				iconCls:'icon-no',
				handler:function(){
					//$("#my_dialog").dialog('close');
					my_destroy("my_dialog");
				}
			}]
    	});
	}

	function logoutFun(b){
		$.getJSON(path+'/test/login',{
			t:new Date()
		},function(result){
			if(b){
				$('.main_header').html('');
				my_dialog();
			}else{
				location.replace(path+'/');

			}
		});
	}
	
	
	function my_form(){
		$.messager.progress({
			//msg:'正在提交数据，请稍后。。。。。。',
			text:'正在提交数据，请稍后。。。。。。',
			//interval:500	
		});
		$("#form").form("submit",{
			url:path+'/test/login',
			onSubmit:function(){
				var isValid = $(this).form('validate');
				if(!isValid){
					$.messager.progress('close');
					//$.messager.alert("提示信息",'您提交的数据不完整','info');
					
					if(!$('#user').val()){
						$('#user').focus();
					}
					
					
				}
				return isValid;
			},
			success:function(data){
				data=$.parseJSON(data);
				console.log(data);
				
				if(data.success){
					$.messager.progress('close');
					my_destroy("my_dialog");
					
					$(".main_header").html("[<strong>"+data.user+"</strong>]，欢迎你！您使用[<strong>"+data.ip+"</strong>]IP登录！")
					
				}else{
					$.messager.progress('close');
					$.messager.alert('错误提示信息',data.msg,'error');
				}
			}
		});
	}
	
	function my_destroy(dialogId){
		$("#"+dialogId).dialog('close');
	}
	
</script>

<style type="text/css">
	
	#form div{
		margin:40px ;
	}
	.main_header{
		position: absolute;
		top: 10px;
		right: 10px	;
	}
	.main_header_menu{
		position: absolute; right: 10px; bottom: 0px;
	}
	.header_h2{
		padding: 0 0 0 15px;
		margin: 0 0 0 15px;
	}
	
</style>

</head>

<body>
	
	<div id="cc" class="easyui-layout" >
	    <div data-options="region:'north'" style="height:80px;border:false">
	    	<div class="page-header">
	    		<h2 class="header_h2">欢迎使用管理系统信息</h2>
	    	</div>
	    	<div class="main_header alert alert-info">
	    		[<strong>admin</strong>]，欢迎你！您使用[<strong>127.0.0.1</strong>]IP登录！
	    	</div>
	    	
	    	<div class="main_header_menu">
	    		<a href="javascript:void(0);" class="easyui-menubutton"  data-options="menu:'#mm',iconCls:'icon-edit'">账号管理</a>
	    	</div>
	    	
	    	<div id="mm" style="width:150px;">
			    <div data-options="iconCls:'icon-undo'" onclick="logoutFun()">注销</div>
			    <div data-options="iconCls:'icon-redo'" onclick="logoutFun('login')">登录</div>
			    <div class="menu-sep"></div>
			    <div data-options="iconCls:'icon-user'" onclick="logoutFun()">退出系统</div>
			</div>
	    	
	    	
	    </div>
	    <div data-options="region:'south',title:'South Title',split:false,border:true" style="height:100px;"></div>
	    <div data-options="region:'east',title:'East',split:false,border:true" style="width:200px;"></div>
	    <div data-options="region:'west',title:'系统菜单',split:false,border:true,collapsible:false" style="width:200px;"></div>
    </div>
	
	<div id="my_dialog">
		<form id="form">
			<div>
				<label for="user">账号：</label>
				<input name="user" id="user" type="text" class="easyui-textbox" data-options="required:true,iconCls:'icon-man',iconAlign:'left'"/>
			</div>
			<div>
				<label for="pwd">密码：</label>
				<input name="pwd" id="pwd" type="password" class="easyui-textbox" data-options="required:true,iconCls:'icon-lock',iconAlign:'left'"/>
			</div>
		</form>
	</div>

</body>
</html>