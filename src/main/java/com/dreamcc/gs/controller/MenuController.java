package com.dreamcc.gs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dreamcc.gs.bean.BaseBean.DELETED;
import com.dreamcc.gs.bean.Menu;
import com.dreamcc.gs.bean.MenuBtn;
import com.dreamcc.gs.bean.TreeNode;
import com.dreamcc.gs.model.SysMenuModel;
import com.dreamcc.gs.serviceimpl.MenuBtnService;
import com.dreamcc.gs.serviceimpl.MenuService;
import com.dreamcc.gs.util.HtmlUtil;
import com.dreamcc.gs.util.TreeUtil;

@Controller
public class MenuController extends BaseController {
	private final static Logger log= Logger.getLogger(MenuController.class);
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private MenuService<Menu> menuService; 
	
	@Autowired(required=false)
	private MenuBtnService<MenuBtn> menuBtnService;
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/menu")
	public ModelAndView menu(SysMenuModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		model.setDeleted(DELETED.NO.key);
		List<Menu> dataList = menuService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("sys/sysMenu",context); 
	}
	
	/**
	 * 顶级菜单 json 
	 * @param menuId 此菜单id不查询，可以为空
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/rootMenuJson") 
	public void  rootMenu(Integer menuId,HttpServletResponse response) throws Exception{
		List<Menu> dataList = menuService.getRootMenu(menuId);
		if(dataList==null){
			dataList = new ArrayList<Menu>();
		}
		HtmlUtil.writerJson(response, dataList);
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(SysMenuModel model,HttpServletResponse response) throws Exception{
		List<Menu> dataList = menuService.queryByList(model);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
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
	public void save(Menu bean,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(true){
			new Exception(" Test Error");
		}
		//设置菜单按钮数据
		List<MenuBtn> btns = getReqBtns(request);
		bean.setBtns(btns);
		if(bean.getId() == null){
			bean.setDeleted(DELETED.NO.key);
			menuService.add(bean);
		}else{
			menuService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		Menu bean  = menuService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<MenuBtn> btns = menuBtnService.queryByMenuid(id);
		bean.setBtns(btns);
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		if(id != null && id.length > 0){
			menuService.delete(id);
			sendSuccessMessage(response, "删除成功");
		}else{
			sendFailureMessage(response, "未选中记录");
		}
	}
	
	
	@RequestMapping("/getMenuTree")
	public void getMenuTree(Integer id,HttpServletResponse response) throws Exception{
		List<TreeNode> menuTree = treeMenu();
		HtmlUtil.writerJson(response, menuTree);
	}
	
	/**
	 * 构建树形菜单
	 * @return
	 */
	public List<TreeNode> treeMenu(){
		List<Menu> rootMenus = menuService.getRootMenu(null);//根节点
		List<Menu> childMenus = menuService.getChildMenu();//子节点
		List<MenuBtn> childBtns = menuBtnService.queryByAll();
		TreeUtil util = new TreeUtil(rootMenus,childMenus,childBtns);
		return util.getTreeNode();
	}
	
	/**
	 * 获取请求的菜单按钮数据
	 * @param request
	 * @return
	 */
	public List<MenuBtn> getReqBtns(HttpServletRequest request){
		List<MenuBtn> btnList= new ArrayList<MenuBtn>();
		String[] btnId  = request.getParameterValues("btnId");
		String[] btnName  = request.getParameterValues("btnName");
		String[] btnType  = request.getParameterValues("btnType");
		String[] actionUrls  = request.getParameterValues("actionUrls");
		String[] deleteFlag  = request.getParameterValues("deleteFlag");
		for (int i = 0; i < btnId.length; i++) {
			if(StringUtils.isNotBlank(btnName[i]) && StringUtils.isNotBlank(btnType[i])){
				MenuBtn btn = new MenuBtn();
				if(StringUtils.isNotBlank(btnId[i]) && NumberUtils.isNumber(btnId[i])){
					btn.setId(NumberUtils.toInt(btnId[i]));
				}
				btn.setBtnName(btnName[i]);
				btn.setBtnType(btnType[i]);
				btn.setActionUrls(actionUrls[i]);
				btn.setDeleteFlag(deleteFlag[i]);
				btnList.add(btn);
			}
		}
		return btnList;
	}
}
