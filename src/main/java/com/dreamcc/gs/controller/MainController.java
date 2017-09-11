package com.dreamcc.gs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dreamcc.gs.bean.BaseBean.DELETED;
import com.dreamcc.gs.bean.BaseBean.STATE;
import com.dreamcc.gs.bean.Menu;
import com.dreamcc.gs.bean.MenuBtn;
import com.dreamcc.gs.bean.TreeNode;
import com.dreamcc.gs.bean.User;
import com.dreamcc.gs.common.Const.SuperAdmin;
import com.dreamcc.gs.model.SiteMainModel;
import com.dreamcc.gs.serviceimpl.MenuBtnService;
import com.dreamcc.gs.serviceimpl.MenuService;
import com.dreamcc.gs.serviceimpl.UserService;
import com.dreamcc.gs.util.DateUtil;
import com.dreamcc.gs.util.HtmlUtil;
import com.dreamcc.gs.util.MethodUtil;
import com.dreamcc.gs.util.SessionUtils;
import com.dreamcc.gs.util.TreeUtil;
import com.dreamcc.gs.util.URLUtils;

@Controller
public class MainController extends BaseController {

	private final static Logger log = Logger.getLogger(MainController.class);

	@Autowired(required = false)
	private MenuService<Menu> menuService;

	@Autowired(required = false)
	private UserService<User> userService;

	@Autowired(required = false)
	private MenuBtnService menuBtnService;

	/**
	 * 登录页面
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("login", context);
	}

	@RequestMapping("/toLogin")
	public void toLogin(String email, String pwd, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		if (StringUtils.isBlank(email)) {
			sendFailureMessage(response, "账号不能为空.");
			return;
		}
		if (StringUtils.isBlank(pwd)) {
			sendFailureMessage(response, "密码不能为空.");
			return;
		}
		String msg = "用户登录日志:";
		User user = userService.queryLogin(email, MethodUtil.MD5(pwd));
		if (user == null) {
			// 记录错误登录日志
			log.debug(msg + "[" + email + "]" + "账号或者密码输入错误.");
			sendFailureMessage(response, "账号或者密码输入错误.");
			return;
		}
		if (STATE.DISABLE.key == user.getState()) {
			sendFailureMessage(response, "账号已被禁用.");
			return;
		}
		// 登录次数加1 修改登录时间
		int loginCount = 0;
		if (user.getLoginCount() != null) {
			loginCount = user.getLoginCount();
		}
		user.setLoginCount(loginCount + 1);
		user.setLoginTime(DateUtil.getDateByString(""));
		userService.update(user);
		// 设置User到Session
		SessionUtils.setUser(request, user);
		// 记录成功登录日志
		log.debug(msg + "[" + email + "]" + "登录成功");
		sendSuccessMessage(response, "登录成功.");
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SessionUtils.removeUser(request);
		response.sendRedirect(URLUtils.get("msUrl") + "/login.shtml");
	}

	/**
	 * 获取Action下的按钮
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getActionBtn")
	public void getActionBtn(String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> actionTypes = new ArrayList<String>();
		// 判断是否超级管理员
		if (SessionUtils.isAdmin(request)) {
			result.put("allType", true);
		} else {
			String menuUrl = URLUtils.getReqUri(url);
			menuUrl = StringUtils.remove(menuUrl, request.getContextPath());
			// 获取权限按钮
			actionTypes = SessionUtils.getMemuBtnListVal(request, StringUtils.trim(menuUrl));
			result.put("allType", false);
			result.put("types", actionTypes);
		}
		result.put(SUCCESS, true);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 修改密码
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPwd")
	public void modifyPwd(String oldPwd, String newPwd, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = SessionUtils.getUser(request);
		if (user == null) {
			sendFailureMessage(response, "对不起,登录超时.");
			return;
		}
		User bean = userService.queryById(user.getId());
		if (bean.getId() == null || DELETED.YES.key == bean.getDeleted()) {
			sendFailureMessage(response, "对不起,用户不存在.");
			return;
		}
		if (StringUtils.isBlank(newPwd)) {
			sendFailureMessage(response, "密码不能为空.");
			return;
		}
		// 不是超级管理员，匹配旧密码
		if (!MethodUtil.ecompareMD5(oldPwd, bean.getPwd())) {
			sendFailureMessage(response, "旧密码输入不匹配.");
			return;
		}
		bean.setPwd(MethodUtil.MD5(newPwd));
		userService.update(bean);
		sendSuccessMessage(response, "Save success.");
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView main(SiteMainModel model, HttpServletRequest request) {
		Map<String, Object> context = getRootMap();
		User user = SessionUtils.getUser(request);
		List<Menu> rootMenus = null;
		List<Menu> childMenus = null;
		List<MenuBtn> childBtns = null;
		// 超级管理员
		if (user != null && SuperAdmin.YES.key == user.getSuperAdmin()) {
			rootMenus = menuService.getRootMenu(null);// 查询所有根节点
			childMenus = menuService.getChildMenu();// 查询所有子节点
		} else {
			rootMenus = menuService.getRootMenuByUser(user.getId());// 根节点
			childMenus = menuService.getChildMenuByUser(user.getId());// 子节点
			childBtns = menuBtnService.getMenuBtnByUser(user.getId());// 按钮操作
			buildData(childMenus, childBtns, request); // 构建必要的数据
		}
		context.put("user", user);
		context.put("menuList", treeMenu(rootMenus, childMenus));
		return forword("main/main", context);
	}

	/**
	 * 构建树形数据
	 * 
	 * @return
	 */
	private List<TreeNode> treeMenu(List<Menu> rootMenus, List<Menu> childMenus) {
		TreeUtil util = new TreeUtil(rootMenus, childMenus);
		return util.getTreeNode();
	}

	/**
	 * 构建树形数据
	 * 
	 * @return
	 */
	private void buildData(List<Menu> childMenus, List<MenuBtn> childBtns, HttpServletRequest request) {
		// 能够访问的url列表
		List<String> accessUrls = new ArrayList<String>();
		// 菜单对应的按钮
		Map<String, List> menuBtnMap = new HashMap<String, List>();
		for (Menu menu : childMenus) {
			// 判断URL是否为空
			if (StringUtils.isNotBlank(menu.getUrl())) {
				List<String> btnTypes = new ArrayList<String>();
				for (MenuBtn btn : childBtns) {
					if (menu.getId().equals(btn.getMenuid())) {
						btnTypes.add(btn.getBtnType());
						URLUtils.getBtnAccessUrls(menu.getUrl(), btn.getActionUrls(), accessUrls);
					}
				}
				menuBtnMap.put(menu.getUrl(), btnTypes);
				URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(), accessUrls);
				accessUrls.add(menu.getUrl());
			}
		}
		SessionUtils.setAccessUrl(request, accessUrls);// 设置可访问的URL
		SessionUtils.setMemuBtnMap(request, menuBtnMap); // 设置可用的按钮
	}
}
