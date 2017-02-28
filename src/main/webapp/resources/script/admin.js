
$(function(){
	var path='/seckill';
	
	$('#nav').tree({
		//url:path+"/test/nav",
		url:path+"/manage/nav",
		lines:true,
		onLoadSuccess:function(node,data){
		
			if (data) {
				$(data).each(function (index, value) {
					if(value.id==1){
						if (this.state == 'closed') {
							$('#nav').tree('expandAll');
						}
					}
				});
			}
			
		},
		onClick:function(node){
			if(node.url){
				if($('#tabs').tabs("exists",node.text)){
					$('#tabs').tabs('select',node.text);
				}else{
					$('#tabs').tabs('add',{
						title:node.text,
						iconCls : node.iconCls,
						closable : true,
						//content:node.text+'tab 的使用'
						href:path+node.url
					});
				}
			}
		},
		
		
//		onContextMenu:function(e,node){
//			e.preventDefault();
//			$('#tt').tree('select', node.target);
//			$('#mm').menu('show', {
//				left: e.pageX,
//				top: e.pageY
//			});
//			$('#mm').menu({
//				 onClick:function(item){
//				   console.log(item);  
//				 }
//			})
//			
//		}
		
		
		
		
	});
	
	
	
	$("#tabs").tabs({
		fit:true,
		border:false
	});
	
	
	$('input[name="siteName"]').validatebox({
		required: true,
		validType : 'length[1,30]',
		missingMessage : '请输入站点名称',
		invalidMessage:'站点名称字数在1-30个字'
	});
	$('input[name="siteUrl"]').validatebox({
		required: true,
		validType : 'url',
		missingMessage : '请输入站点地址',
		invalidMessage:'您输入的站点地址不符合要求'
	});
	
	$('input[name="siteKeyword"]').validatebox({
		required: true,
		validType : 'length[1,100]',
		missingMessage : '请输入站点关键词',
		invalidMessage:'您输入的站点关键词过长，最多不超过100个汉字'
	});
	
	$('textarea[name="siteDescription"]').validatebox({
		required: true,
		validType : 'length[1,250]',
		missingMessage : '请输入站点描述',
		invalidMessage:'您输入的站点描述词过长，最多不超过250个汉字'
	});
	$('input[name="siteCopyright"]').validatebox({
		required: true,
		validType : 'length[1,250]',
		missingMessage : '请输入站点版权信息',
		invalidMessage:'您输入的站点版权信息过长，最多不超过100个字符'
	});
	
	$('input[name="file"]').filebox({
		buttonText:'上传',
		buttonAlign:'right'	,
		buttonIcon:'icon-search',
		accept: 'image/*',
		onChange:function(newValue,oldValue){
			
			
			
			
		}
	});
	
	
	$("#site_btn").bind('click',function(){
		
		
		if($("#site_form").form("validate")){
			$.ajax({
				url:path+'/manage/site/updateSite',
				type:'post',
				data:{
					siteId:$('input[name="siteId"]').val(),
					siteName:$('input[name="siteName"]').val(),
					siteUrl:$('input[name="siteUrl"]').val(),
					siteKeyword:$('input[name="siteKeyword"]').val(),
					siteDescription:$('textarea[name="siteDescription"]').val(),
					siteCopyright:$('input[name="siteCopyright"]').val(),
					siteLogo:$('input[name="siteLogo"]').val(),
				},
				beforeSend:function(){
					$.messager.progress({
						text:'正在提交数据。。。。。。'
					});
				},
				success:function(data){
					$.messager.progress("close");
					if(data&&data.success){
						$.messager.show({
							title:'提示',
							msg:data.msg,
						});
					}else{
						$.messager.alert('提示','数据提交失败','warning');
					}
					
				}
				
			});
		}
		
		
		
		
	});
	
});
