<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/views/resource.jsp"%>
<script type="text/javascript" src="${webUrl}/js/common/YDataGrid.js"></script>
<script type="text/javascript" src="${webUrl}/js/sys/param.js"></script>
</head>
<body>
	<div class="warp easyui-panel" data-options="border:false">

 	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="Search box" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
           <label class="ui-label">参数:</label> 
           <input name="sysParamField" class="easyui-box ui-text" style="width:100px;">
        </p>
        <p class="ui-fields">
           <label class="ui-label">选项值:</label> 
           <input name="sysParamText" class="easyui-box ui-text" style="width:100px;">
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
		     	   <div class="ftitle">参数信息</div>    
		     	   <div class="fitem">  
		               <label>参数类型:</label>  
		               <input class="easyui-validatebox" type="text" name="sysParamField" data-options="required:true">
		           </div>  
		           <div class="fitem">  
		               <label>参数值:</label>  
		               <input class="easyui-validatebox" type="text" name="sysParamValue" data-options="required:true">
		           </div>  
		            <div class="fitem">  
		               <label>显示名称:</label>  
		               <input class="easyui-validatebox" type="text" name="sysParamText" data-options="required:true">
		           </div> 					           	           	
	         </div>
     	</form>
  	 </div> 
	</div>
  </body>
</html>