$package('dreamcc.supplier');

dreamcc.supplier = function(){
	var _box = null;
	var _this = {
			config:{
  			dataGrid:{
  				title:'商品列表',
  				url:'dataList',
	   			columns:[[
	   				{field:'id',title:'商品id',width:120,checkbox:true,sortable:true},
	   				{field:'goodsName',title:'商品名称',width:120,sortable:true},
	   				{field:'goodsUnit',title:'商品单位',width:120,sortable:true},
	   				{field:'goodsType',title:'商品类型',width:120,sortable:true},
	   				{field:'goodsColor',title:'商品颜色',width:120,sortable:true},
	   				{field:'goodsStore',title:'商品下限',width:120,sortable:true},
	   				{field:'goodsLimit',title:'有效修改时间',width:120,sortable:true},
	   				{field:'goodsCommission',title:'提成',width:120,sortable:true},
	   				{field:'goodsProducer',title:'生产商',width:120,sortable:true},
	   				{field:'goodsRemark',title:'备注',width:120,sortable:true},
	   				{field:'goodsSelPrice',title:'售价',width:120,sortable:true},
	   				{field:'goodsBuyPrice',title:'进价',width:120,sortable:true}		
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