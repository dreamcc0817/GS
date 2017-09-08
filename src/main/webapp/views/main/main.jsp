<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/views/resource.jsp"%>
    <link rel="stylesheet" type="text/css" href="css/main/main.css">
    <script type="text/javascript" src="js/main/main.js"></script>
<title>智慧平台</title>
</head>
<body class="easyui-layout">

	<div class="ui-header"
		data-options="region:'north',split:true,border:false"
		style="height: 40px; overflow: hidden;">
		<h1>Li_U_Cha_N_g</h1>
		<div class="ui-login">
			<div class="ui-login-info">
				欢迎 <span class="orange">${user.nickName}</span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="modify-pwd-btn"
					href="javascript:void(0);">修改密码</a> | <a class="logout-btn"
					href="${msUrl}/logout.html">退出</a>
			</div>
		</div>
	</div>

	<div data-options="region:'west',split:true,title:'West'"
		style="width: 150px; padding: 10px;">
		<div id="tree-box" class="easyui-accordion"
			data-options="fit:true,border:false">
			<c:forEach var="item" items="${menuList}">
				<div title="${item.text}">
					<c:forEach var="node" items="${item.children}">
						<a class="menu-item" href="${msUrl}${node.url}">${node.text}</a>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
	<div
		data-options="region:'east',split:true,collapsed:true,title:'East'"
		style="width: 100px; padding: 10px;">east region</div>
	<div data-options="region:'south',border:false"
		style="height: 50px; background: #A9FACD; padding: 10px;">
		<div class="panel-header" style="border: none; text-align: center;">CopyRight
			&copy; 2017 Swing dreamcc版权所有.</div>
	</div>
	<div data-options="region:'center',title:'Center'">
		<div class="easyui-tabs" id="tab-box"
			data-options="fit:true,border:false">
			<div title="Welcome" style="padding: 20px; overflow: hidden;">
			</div>
		</div>
	</div>



	<!--  modify password start -->
	<div id="modify-pwd-win" class="easyui-dialog" buttons="#editPwdbtn"
		title="修改用户密码"
		data-options="closed:true,iconCls:'icon-save',modal:true"
		style="width: 350px; height: 200px;">
		<form id="pwdForm" action="modifyPwd.do" class="ui-form" method="post">
			<input class="hidden" name="id"> <input class="hidden"
				name="email">
			<div class="ui-edit">
				<div class="fitem">
					<label>旧密码:</label> <input id="oldPwd" name="oldPwd"
						type="password" class="easyui-validatebox"
						data-options="required:true" />
				</div>
				<div class="fitem">
					<label>新密码:</label> <input id="newPwd" name="newPwd"
						type="password" class="easyui-validatebox"
						data-options="required:true" />
				</div>
				<div class="fitem">
					<label>重复密码:</label> <input id="rpwd" name="rpwd" type="password"
						class="easyui-validatebox" required="required"
						validType="equals['#newPwd']" />
				</div>
			</div>
		</form>
		<div id="editPwdbtn" class="dialog-button">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				id="btn-pwd-submit">Submit</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" id="btn-pwd-close">Close</a>
		</div>
	</div>
</body>
</html>