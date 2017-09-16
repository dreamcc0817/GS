package com.dreamcc.gs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dreamcc.gs.bean.Menu;
import com.dreamcc.gs.bean.Role;
import com.dreamcc.gs.bean.RoleRel;
import com.dreamcc.gs.bean.RoleRel.RelType;
import com.dreamcc.gs.model.SysRoleModel;
import com.dreamcc.gs.serviceimpl.MenuService;
import com.dreamcc.gs.serviceimpl.RoleRelService;
import com.dreamcc.gs.serviceimpl.RoleService;
import com.dreamcc.gs.util.HtmlUtil;

/**
 * 
 * @description
 * @author 刘畅
 * @date 2017年9月14日
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	private final static Logger log = Logger.getLogger(RoleController.class);

	@Autowired(required=false)
	private RoleService<Role> roleService;
	@Autowired(required=false)
	private MenuService<Menu> menuService;
	@Autowired(required=false)
	private RoleRelService<RoleRel> roleRelService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/roles")
	public ModelAndView list(SysRoleModel model, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("user/role", context);
	}

	/**
	 *  首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void dataList(SysRoleModel model, HttpServletResponse response) throws Exception {
		List<Role> dataList = roleService.queryByList(model);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(Role bean,Integer[] menuIds,Integer[] btnIds,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			roleService.add(bean,menuIds,btnIds);
		}else{
			roleService.update(bean,menuIds,btnIds);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object> ();
		Role bean  = roleService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		//获取权限关联的菜单
		Integer[] menuIds = null;
		List<Menu> menuList =  menuService.getMenuByRoleId(id);
		if(menuList != null){
			menuIds = new Integer[menuList.size()];
			int i = 0;
			for(Menu item : menuList){
				menuIds[i] = item.getId();
				i++;
			}
		}
		//获取权限下关联的按钮
		Integer[] btnIds = null;
		List<RoleRel>  btnList = roleRelService.queryByRoleId(id, RelType.BTN.key);
		if(btnList != null){
			btnIds = new Integer[btnList.size()];
			int i = 0;
			for(RoleRel item : btnList){
				btnIds[i] = item.getObjId();
				i++;
			}
		}

		//将对象转成Map
		Map<String,Object> data = BeanUtils.describe(bean);
		data.put("menuIds", menuIds);
		data.put("btnIds", btnIds);
		context.put(SUCCESS, true);
		context.put("data", data);
		HtmlUtil.writerJson(response, context);
	}	

	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		roleService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	
	
	@RequestMapping("/loadRoleList")
	public void loadRoleList(HttpServletResponse response) throws Exception{
		List<Role>  roloList = roleService.queryAllList();
		HtmlUtil.writerJson(response, roloList);
	}


}
