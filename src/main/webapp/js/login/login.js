$package('dreamcc.login');

dreamcc.login = function(){
	return{
		toLogin:function(){
			try{
				var form = $("#loginForm");
				if(form.form('validate')){
					dreamcc.progress('Please waiting','Loding...');
					dreamcc.submitForm(form,function(data){
						dreamcc.closeProgress();
						if(data.success){
							window.location= "main.html";
						}
					});
				}
			}catch(e){
				
			}
			return false;
		},
		init:function(){
			if(window.top != window.self){
				window.top.location =  window.self.location;
			}			
			var form = $("#loginForm");
			form.submit(dreamcc.login.toLogin);
		}
	}
}();

$(function(){
	dreamcc.login.init();
});		