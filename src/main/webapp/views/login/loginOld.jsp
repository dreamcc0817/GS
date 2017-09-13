<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户登录</title>
		<%@include file="/views/resource.jsp" %>
		<%
		pageContext.setAttribute("APP_PATH",request.getContextPath());
		%>
				<script type="text/javascript" src="${APP_PATH}/js/sys/login.js"></script>
    	<link rel="stylesheet" type="text/css" href="${APP_PATH}/css/main/mainOld.css">
    	<link rel="stylesheet" type="text/css" href="${APP_PATH}/css/login/loginOld.css">
	</head>
	<form id="loginForm" action="toLogin.do" method="post">
	<body id="userlogin_body">
		<div></div>
		<div id=user_login>
			<dl>
				<dd id=user_top>
					<ul>
						<li class=user_top_l></li>
						<li class=user_top_c></li>
						<li class=user_top_r></li>
					</ul>
				<dd id=user_main>
					<ul>
						<li class=user_main_l></li>
						<li class=user_main_c>
							<div class=user_main_box>
								<ul>
									<li class=user_main_text>
										用户名：
									</li>
									<li class=user_main_input>
										<input class="txtusernamecssclass easyui-validatebox"  data-options="required:true,validType:'email',missingMessage:'邮箱不能为空.',invalidMessage:'邮箱格式不正确'" name="email"  value="admin@qq.com" maxlength="20">
									</li>
								</ul>
								<ul>
									<li class=user_main_text>
										密 码：
									</li>
									<li class=user_main_input>
										<input class="txtpasswordcssclass easyui-validatebox"   data-options="required:true,missingMessage:'密码不能为空.'" type="password" name="pwd" value="654321">
									</li>
								</ul>
							</div>
						</li>
						<li class=user_main_r>
							<input class="ibtnentercssclass"
								style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px"
								type=image src="./img/login/user_botton.gif">
						</li>
					</ul>
			</dl>
		</div>
		<div></div>
		</form>
	</body>
</html>
