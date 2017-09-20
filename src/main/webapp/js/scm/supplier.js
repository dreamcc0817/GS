$package('dreamcc.supplier');


//$(function(){
//	$('#data-list').datagrid({
//		title:'供应商列表',
//		url:'dataList',
//		columns:[[
//				{field:'supId',chekbox:true},
//				{field:'supName',title:'供应商名称',width:120},
//				{field:'supLinkman',title:'联系人姓名',width:120},
//				{field:'supPhone',title:'联系人电话',width:120},
//				{field:'supAddress',title:'供应商地址',width:120},
//				{field:'supType',title:'级别',width:120,sortable:true}	 
//		]]
//	});
//});
dreamcc.supplier = function(){
	var _box = null;
	var _this = {
			config:{
  			dataGrid:{
  				title:'供应商列表',
  				url:'dataList',
	   			columns:[[
	   				{field:'id',checkbox:true},
	   				{field:'supName',title:'供应商名称',width:120,sortable:true},
	   				{field:'supLinkman',title:'联系人姓名',width:120,sortable:true},
	   				{field:'supPhone',title:'联系人电话',width:120,sortable:true},
	   				{field:'supAddress',title:'供应商地址',width:120,sortable:true},
	   				{field:'supType',title:'级别',width:120,sortable:true}	   				
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
	dreamcc.supplier.init();
});