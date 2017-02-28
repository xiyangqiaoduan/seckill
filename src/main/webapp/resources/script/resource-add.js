$(function(){
	var path='/seckill'
	
	$('#pid').combotree({
		url : path+'/manage/nav',
		lines : true,
		panelHeight : 'auto',
		onLoadSuccess : function() {
			
		}
	});
	
	
});