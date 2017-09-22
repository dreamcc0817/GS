<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/views/resource.jsp"%>
<script type="text/javascript" src="${webUrl}/js/common/YDataGrid.js"></script>
<script type="text/javascript" src="${webUrl}/js/scm/goods.js"></script>
</head>
<body>
	<div class="warp easyui-panel" data-options="border:false">

 	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="Search box" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
           <label class="ui-label">商品:</label> 
           <input name="goodsName" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
      </form>  
     </div> 
     <!--  Search panel end -->

     <!-- Data List -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
	 </form>
     <!-- Edit Win&Form -->
      <div id="edit-win" class="easyui-dialog" title="增加商品" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:380px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
		     	   <div class="ftitle">商品信息</div>    
		     	   <div class="fitem">  
		               <label>商品编号:</label>  
		               <input class="easyui-validatebox" type="text" name="id" data-options="required:true">
		           </div>  
		           <div class="fitem">  
		               <label>商品名称:</label>  
		               <input class="easyui-validatebox" type="text" name="goodsName" data-options="required:true">
		           </div>  
		            <div class="fitem">  
		               <label>商品单位:</label>  
		               <input class="easyui-validatebox" type="text" name="goodsUnit" data-options="required:true">
		           </div> 
		            <div class="fitem">  
		               <label>商品类型:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsType" data-options="required:true,validType:'phone'">
		           </div> 
		            <div class="fitem">  
		               <label>商品颜色:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsColor" data-options="required:true,validType:'phone'">
		           </div> 
		           <div class="fitem">  
		               <label>商品下限:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsStore" data-options="required:true">
		           </div> 
		           <div class="fitem">  
		               <label>有效修改时间:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsLimit" data-options="required:true">
		           </div> 
		           <div class="fitem">  
		               <label>提成:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsCommission" data-options="required:true">
		           </div> 	
		           <div class="fitem">  
		               <label>生产商:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsProducer" data-options="required:true">
		           </div>  	
		           <div class="fitem">  
		               <label>备注:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsRemark" data-options="required:true">
		           </div>  	
		           <div class="fitem">  
		               <label>售价:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsSelPrice" data-options="required:true">
		           </div> 	 	
		           <div class="fitem">  
		               <label>进价:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="goodsBuyPrice" data-options="required:true">
		           </div> 							           	           	
	         </div>
     	</form>
  	 </div> 
	</div>
  </body>
</html>