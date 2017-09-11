package com.dreamcc.gs.service;

import java.util.List;

import com.dreamcc.gs.bean.Menu;
import com.dreamcc.gs.bean.MenuBtn;
import com.dreamcc.gs.common.ServerResponse;

public interface IMenuService {
	/**
	 * 保存菜单btn
	 * @param menuid
	 * @param btns
	 * @return
	 */
	ServerResponse<MenuBtn> saveBtn(Integer menuid,List<MenuBtn> btns);
	/**
	 * 增加菜单btn
	 * @param menu
	 * @return
	 */
	ServerResponse<MenuBtn> add(Menu menu);
	/**
	 * 更新菜单btn
	 * @param menu
	 * @return
	 */
	ServerResponse<MenuBtn> update(Menu menu);
	/**
	 * 查询所有系统的菜单
	 * @return
	 */
	ServerResponse<Menu> queryByAll();
	/**
	 * 获取顶级菜单
	 * @param menuId
	 * @return
	 */
	ServerResponse<Menu> getRootMenu(Integer menuId);
	/**
	 * 获取子菜单
	 * @return
	 */
	ServerResponse<Menu> getChileMenu();
	/**
	 * 根据用户id查询父菜单
	 * @param userId
	 * @return
	 */
	ServerResponse<Menu> getRootMenuByUser(Integer userId);
	/**
	 * 根据用户id查询子菜单
	 * @param userId
	 * @return
	 */
	ServerResponse<Menu> getChildMenuByUser(Integer userId);
	/**
	 * 根据角色id查询菜单
	 * @param roleId
	 * @return
	 */
	ServerResponse<Menu> getMenuByRoleId(Integer roleId);
	void delete();
}
