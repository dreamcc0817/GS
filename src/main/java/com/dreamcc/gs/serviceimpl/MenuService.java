package com.dreamcc.gs.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.bean.Menu;
import com.dreamcc.gs.bean.MenuBtn;
import com.dreamcc.gs.bean.RoleRel;
import com.dreamcc.gs.bean.RoleRel.RelType;
import com.dreamcc.gs.dao.MenuMapper;

@Service("menuService")
public class MenuService<T> extends BaseService<T> {

	private final static Logger log = Logger.getLogger(MenuService.class);
	
	@Autowired
	private RoleRelService<RoleRel> roleRelService;
	
	@Autowired
	private MenuBtnService<MenuBtn> menuBtnService;
	
	@Autowired
	private MenuMapper<T> mapper;
	
	public MenuMapper<T> getMapper(){
		return mapper;
	}
	/**
	 * 保存菜单按钮
	 * @param menuid
	 * @param btns
	 * @throws Exception
	 */
	public void saveBtns(Integer menuid,List<MenuBtn> btns) throws Exception{
		if(btns == null || btns.isEmpty()){
			return;
		}
		for(MenuBtn btn : btns){
			if(btn.getId() != null && "1".equals(btn.getDeleteFlag())){
				menuBtnService.delete(btn.getId());
				continue;
			}
			btn.setMenuid(menuid);
			if(btn.getId() == null){
				menuBtnService.add(btn);
			}else{
				menuBtnService.update(btn);
			}
		}
	}
	
	public void add(Menu menu) throws Exception{
		super.add((T)menu);
		saveBtns(menu.getId(),menu.getBtns());
	}
	public void update(Menu menu) throws Exception {
		super.update((T)menu);
		saveBtns(menu.getId(),menu.getBtns());
	}
	
	/**
	 * 查询所有系统菜单列表
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * 获取顶级菜单
	 * @return
	 */
	public List<T> getRootMenu(Integer menuId){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("menuId", menuId);
		return mapper.getRootMenu(map);
	}
	
	/**
	 * 获取子菜单
	 * @return
	 */
	public List<T> getChildMenu(){
		return mapper.getChildMenu();
	}
	
	/**
	 * 根据用户id查询父菜单
	 * @param roleId
	 * @return
	 */
	public List<T> getRootMenuByUser(Integer userId){
		return getMapper().getRootMenuByUser(userId);
	}
	
	
	/**
	 * 根据用户id查询子菜单
	 * @param roleId
	 * @return
	 */
	public List<T> getChildMenuByUser(Integer userId){
		return getMapper().getChildMenuByUser(userId);
	}
	
	
	/**
	 * 根据权限id查询菜单
	 * @param roleId
	 * @return
	 */
	public List<T> getMenuByRoleId(Integer roleId){
		return getMapper().getMenuByRoleId(roleId);
	}
	
	
	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		//删除关联关系
		for(Object id : ids){
			roleRelService.deleteByObjId((Integer)id, RelType.MENU.key);
			menuBtnService.deleteByMenuid((Integer)id);
		}
	}
}
