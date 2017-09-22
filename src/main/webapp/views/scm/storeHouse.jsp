<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/views/resource.jsp"%>
<script type="text/javascript" src="${webUrl}/js/common/YDataGrid.js"></script>
<script type="text/javascript" src="${webUrl}/js/scm/storeHouse.js"></script>
</head>
<body>
	<div class="warp easyui-panel" data-options="border:false">

 	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="Search box" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
           <label class="ui-label">仓库:</label> 
           <input name="shName" class="easyui-box ui-text" style="width:100px;">
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
      <div id="edit-win" class="easyui-dialog" title="增加仓库" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:380px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
		     	   <div class="ftitle">仓库信息</div>    
		           <div class="fitem">  
		               <label>仓库名称:</label>  
		               <input class="easyui-validatebox" type="text" name="shName" data-options="required:true">
		           </div>  
		            <div class="fitem">  
		               <label>责任人:</label>  
		               <input class="easyui-validatebox" type="text" name="shResponsible" data-options="required:true">
		           </div> 
		            <div class="fitem">  
		               <label>联系人电话:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="shPhone" data-options="required:true,validType:'phone'">
		           </div> 
		           <div class="fitem">  
		               <label>仓库地址:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="shAddress" data-options="required:true">
		           </div> 
		           <div class="fitem">  
		               <label>仓库类别:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="shType" data-options="required:true">
		           </div> 
		           <div class="fitem">  
		               <label>备注:</label>  
		               <input class="easyui-validatebox" type="text" value="" name="shType" data-options="required:true">
		           </div> 
	         </div>
     	</form>
  	 </div> 
	</div>
  </body>
</html>