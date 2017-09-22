$package('dreamcc.storeHouse');

dreamcc.storeHouse = function(){
	var _box = null;
	var _this = {
			config:{
  			dataGrid:{
  				title:'供应商列表',
  				url:'dataList',
	   			columns:[[
	   				{field:'id',checkbox:true},
	   				{field:'shName',title:'仓库名称',width:120,sortable:true},
	   				{field:'shResponsible',title:'负责人',width:120,sortable:true},
	   				{field:'shPhone',title:'联系人电话',width:120,sortable:true},
	   				{field:'shAddress',title:'仓库地址',width:120,sortable:true},
	   				{field:'shType',title:'仓库类型',width:120,sortable:true},
	   				{field:'shRemark',title:'备注',width:120,sortable:true}	 
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
	dreamcc.storeHouse.init();
});