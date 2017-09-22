$package('dreamcc.param');

dreamcc.param = function(){
	var _box = null;
	var _this = {
			config:{
  			dataGrid:{
  				title:'参数列表',
  				url:'dataList',
	   			columns:[[
	   				{field:'id',checkbox:true},
	   				{field:'sysParamField',title:'参数类型',width:120,sortable:true},
	   				{field:'sysParamValue',title:'参数值',width:120,sortable:true},
	   				{field:'sysParamText',title:'显示名称',width:120,sortable:true}		
	   			]],
				toolbar:[
					{id:'btnadd',text:'增加',btnType:'add'},
					{id:'btnedit',text:'编辑',btnType:'edit'},
					{id:'btndelete',text:'删除',btnType:'remove'}
				]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	dreamcc.param.init();
});