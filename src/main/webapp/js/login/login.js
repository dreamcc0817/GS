$package('dreamcc.login');
dreamcc.login = function(){
	return {
		toLogin:function(){
			try{
				var form = $("#loginForm");
				if(form.form('validate')){
					dreamcc.progress('Please waiting','Loading...');
					dreamcc.submitForm(form,function(data){
						dreamcc.closeProgress();
						dreamcc.login.loadVrifyCode();//刷新验证码
						if(data.success){
					 		window.location= "views/main/main.jsp";
				        }else{
				       	   dreamcc.alert('提示',data.msg,'error');
				        }
					});
				}
			}catch(e){
				
			}
			return false;
		},
		loadVrifyCode:function(){//刷新验证码
			var _url = "ImageServlet?time="+new Date().getTime();
			$(".vc-pic").attr('src',_url);
		},
		init:function(){
			if(window.top != window.self){
				window.top.location =  window.self.location;
			}
			//验证码图片绑定点击事件
			$(".vc-pic").click(dreamcc.login.loadVrifyCode);
			
			var form = $("#loginForm");
			form.submit(dreamcc.login.toLogin);
		}
	}
}();

$(function(){
	dreamcc.login.init();
});		