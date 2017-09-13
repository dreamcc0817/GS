<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/views/resource.jsp" %>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<script type="text/javascript" src="${APP_PATH}/js/common/YDataGrid.js"></script>
<script type="text/javascript" src="${APP_PATH}/js/sys/user.js"></script>
</head>
<body>
	<div class="wrap easyui-panel" data-options="border:false">
		<!-- 搜索Form -->
		<div class="easyui-panel ui-search-panel" title="Search box" data-option="striped: true,collapsible:true,iconCls:'icon-search'">
			<form id="searcherForm">
				<p class="ui-fields">
				  <label class="ui-label">Email:</label><input name="email" class="easyui-box ui-text" style="width:100px;">
            	  <label class="ui-label">NickName: </label><input name="nickName" class="easyui-box ui-text" style="width:100px;">
        		</p>
			</form>
		</div>
		<!-- 数据框 -->
		<form id="listForm" method="post">
			<table id="data-list"></table>
		</form>
		<!-- 编辑框 -->
		<div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">
		 <form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     		 <input class="hidden" name="deleted">
     		 <div class="ui-edit">
	     	   <div class="ftitle">用户信息</div>    
	           <div class="fitem">  
	               <label>Email:</label>  
	               <input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'email'">
	           </div>  
	            
	           <div class="fitem">  
	               <label>姓名:</label>  
	               <input class="easyui-validatebox" type="text" name="nickName" data-options="required:true,validType:'length[1,10]'">
	           </div>
	            <div class="fitem">  
	               <label>State:</label>  
	               <select class="easyui-combobox" name="state" data-options="required:true">
                    	<option value="0" selected="selected">可用</option>
                    	<option value="1">禁用</option>
                   	</select>
	           </div> 
	         </div>
     	 </form>
		</div>
		<!-- 修改密码Form -->
		     <div id="edit-pwd-win" class="easyui-dialog" buttons="#editPwdbtn" title="Edit PassWord" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">
     	<form id="pwdForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
	     	   <div class="ftitle">用户信息</div>    
	           <div class="fitem">  
	               <label>Email:</label>  
	               <input class="easyui-validatebox" type="text" readonly="readonly" name="email" data-options="required:true,validType:'email'">
	           </div>  
	           <div class="fitem">  
	              <label>旧密码:</label>  
	              <input id="oldPwd" name="oldPwd" type="password" class="easyui-validatebox"/>
	           </div>
	            <div class="fitem">  
	               <label>新密码:</label>  
	               <input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" />
	           </div> 
	           <div class="fitem">  
	               <label>确认密码:</label>  
	              <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"   required="required" validType="equals['#newPwd']" />
	           </div> 
	         </div>
     	</form>
     	 <div id="editPwdbtn" class="dialog-button">  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-submit">提交</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-close">关闭</a>  
        </div>
  	 </div> 
	</div>
</body>
</html>