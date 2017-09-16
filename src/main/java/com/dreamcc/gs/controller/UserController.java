package com.dreamcc.gs.controller;

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
import com.dreamcc.gs.bean.Role;
import com.dreamcc.gs.bean.RoleRel;
import com.dreamcc.gs.bean.User;
import com.dreamcc.gs.exception.ServiceException;
import com.dreamcc.gs.model.SysUserModel;
import com.dreamcc.gs.serviceimpl.RoleService;
import com.dreamcc.gs.serviceimpl.UserService;
import com.dreamcc.gs.util.HtmlUtil;
import com.dreamcc.gs.util.MethodUtil;
import com.dreamcc.gs.util.SessionUtils;

/**
 * 
 * @description baseController
 * @author 刘畅
 * @date 2017年9月9日
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private final static Logger log = Logger.getLogger(UserController.class);
	@Autowired(required=false)
	private UserService<User> userService;
	@Autowired(required=false)
	private RoleService<Role> roleService;
	/**
	 * 人员列表
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(SysUserModel model,HttpServletRequest request) throws Exception{
		Map<String,Object> context = getRootMap();
		List<User> dataList = userService.queryByList(model);
		context.put("dataList", dataList);
		return forword("user/user",context);
	}
	/**
	 * json 列表页面
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void dataList(SysUserModel model,HttpServletResponse response) throws Exception{
		List<User> dataList = userService.queryByList(model);
		for(User user:dataList){
			List<Role> list = roleService.queryByUserId(user.getId());
			user.setRoleStr(rolesToStr(list));
		}
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * 角色列表转成字符串
	 * @param list
	 * @return
	 */
	private String rolesToStr(List<Role> list){
		if(list == null || list.isEmpty()){
			return null;
		}
		StringBuffer str = new StringBuffer();
		for(int i=0;i<list.size();i++){
			Role role = list.get(i);
			str.append(role.getRoleName());
			if((i+1) < list.size()){
				str.append(",");
			}
		}
		return str.toString();
	}
	/**
	 * 添加或修改数据
	 * @param bean
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(User bean,HttpServletResponse response) throws Exception{
		Map<String,Object> context = new HashMap<String,Object>();
		int count = userService.getUserCountByEmail(bean.getEmail());
		if(bean.getId() == null){
			if(count > 0){
				throw new ServiceException("用户已存在.");
			}
			bean.setDeleted(DELETED.NO.key);
			userService.add(bean);
		}else{
			if(count > 1){
				throw new ServiceException("用户已存在.");
			}
			userService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		User bean  = userService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		userService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updatePwd")
	public void updatePwd(Integer id,String oldPwd,String newPwd,HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean isAdmin = SessionUtils.isAdmin(request); //是否超级管理员
		User bean  = userService.queryById(id);
		if(bean.getId() == null || DELETED.YES.key == bean.getDeleted()){
			sendFailureMessage(response, "Sorry ,User is not exists.");
			return;
		}
		if(StringUtils.isBlank(newPwd)){
			sendFailureMessage(response, "Password is required.");
			return;
		}
		//不是超级管理员，匹配旧密码
		if(!isAdmin && !MethodUtil.ecompareMD5(oldPwd,bean.getPwd())){
			sendFailureMessage(response, "Wrong old password.");
			return;
		}
		bean.setPwd(MethodUtil.MD5(newPwd));
		userService.update(bean);
		sendSuccessMessage(response, "保存成功~");
	}
	/**
	 * 用户授权页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userRole") 
	public ModelAndView  userRole(HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("/user/userRole", context);
	}
	
	/**
	 * 用户授权列表
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/userList") 
	public void  userList(SysUserModel model,HttpServletResponse response) throws Exception{
		model.setState(STATE.ENABLE.key);
		dataList(model, response);
	}

	/**
	 * 查询用户信息
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getUser") 
	public void getUser(Integer id,HttpServletResponse response)  throws Exception{
		Map<String,Object>  context = getRootMap();
		User bean  = userService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		Integer[] roleIds = null;
		List<RoleRel>  roles  = userService.getUserRole(bean.getId());
		if(roles != null){
			roleIds = new Integer[roles.size()];
			int i = 0;
			for(RoleRel rel : roles ){
				roleIds[i] = rel.getRoleId();
				i++;
			}
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", bean.getId());
		data.put("email", bean.getEmail());
		data.put("roleIds", roleIds);
		context.put(SUCCESS, true);
		context.put("data", data);
		HtmlUtil.writerJson(response, context);
		
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addUserRole")
	public void addUserRole(Integer id,Integer roleIds[],HttpServletResponse response) throws Exception{
		userService.addUserRole(id, roleIds);
		sendSuccessMessage(response, "保存成功");
	}
}
