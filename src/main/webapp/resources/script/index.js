
$(function(){
	
	var path='/seckill';
	
	$("#login").dialog({
		title:'后台系统登录',
		width:300,
		height:180,
		modle:true,
		iconCls : 'icon-login',
		buttons:'#btn'
	});
	
	$("#manager").validatebox({
		required:true,
		missingMessage:'请输入管理员账号',
		invalidMessage:'管理员帐号不得为空'
	});
	$("#password").validatebox({
		required:true,
		validType:'length[6,30]',
		missingMessage : '请输入管理员密码',
		invalidMessage : '管理员密码不能少于6位',
	});
	
	
	//加载时判断验证
	if (!$('#manager').validatebox('isValid')) {
		$('#manager').focus();
	}else if (!$('#password').validatebox('isValid')) {
		$('#password').focus();
	}
	
	$("#btn a").click(function(){
		
		if (!$('#manager').validatebox('isValid')) {
			$('#manager').focus();
		}else if (!$('#password').validatebox('isValid')) {
			$('#password').focus();
		}else{
			$.ajax({
				url:path+'/test/login',
				type:'post',
				data:{
					user:$('#manager').val(),
					pwd:$('#password').val()
				},
				beforeSend :function(){
					$.messager.progress({
						text:'正在登录中。。。。。。'
					});
				},
				success:function(data, response, status){
					
					//data=$.parseJSON(data);
					$.messager.progress('close');
					if(data.success&&data.success=='true'){
						location.href=path+'/test/admin';
					}else{
						$.messager.alert('登录失败！','用户或者密码错误','warning',function(){
							$('#password').select();
						});
					}
					
					
				}
			});
		}
		
	});
	
	
});