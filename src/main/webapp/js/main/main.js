$package("dreamcc.main");

dreamcc.main = function(){
	return{
		treeSelect:function(){
			var _this = $(this);
			var title = _this.text();
			var url = this.attr("href");
			dreamcc.main.addTab(title,url);
			return false;
		},// treeSelect
		treeInit:function(){
			var $item = $("#tree-box").find(".menu-item");
			$items.bind('click',this.treeSelect);
		},// treeInit
		addTab:function(_title,_url){
			var boxId = "#tab-box";
			if($(boxId).tabs("exists",title)){
				var tab = $(boxId).tabs("getTab",_title);
				var index = $(boxId).tabs("getTabIndex",tab);
				$(boxId).tabs('select',index);
				if(tab && tab.find('iframe').length > 0){  
					 var _refresh_ifram = tab.find('iframe')[0];  
				     _refresh_ifram.contentWindow.location.href=_url;  
			    } 
			}else{		
				var _content ="<iframe scrolling='auto' frameborder='0' src='"+_url+"' style='width:100%; height:100%'></iframe>";
				$(boxId).tabs('add',{
					    title:_title,
					    content:_content,
					    closable:true});				
			}
		},// addTab
		menuHover:function(){
			//菜单鼠标进入效果
			$('.menu-item').hover(
				function () {
					$(this).stop().animate({ paddingLeft: "25px" }, 200,function(){
						$(this).addClass("orange");
					});
				}, 
				function () {
					$(this).stop().animate({ paddingLeft: "15px" },function(){
						$(this).removeClass("orange");
					});
				}
			);
		},//menuHover
		modifyPwd:function(){
			var pwdForm = $("#pwdForm");
			if(pwdForm.form('validate')){
				var parentId =$('#search_parentId').val();
				$("#edit_parentId").val(parentId)
				YiYa.saveForm(pwdForm,function(data){
					$('#modify-pwd-win').dialog('close');
				    pwdForm.resetForm();
				});
			 }
		},
		init:function(){
			this.treeInit();
			this.menuHover();
			
			//修改密码绑定事件
			$('.modify-pwd-btn').click(function(){
				$('#modify-pwd-win').dialog('open');
			});
			$('#btn-pwd-close').click(function(){
				$('#modify-pwd-win').dialog('close');
			});
			$('#btn-pwd-submit').click(this.modifyPwd);
			
		}
	};
}();

$(function(){
	dreamcc.main.init();
});		