package com.dreamcc.gs.dao;

import java.util.List;
import java.util.Map;

import com.dreamcc.gs.bean.Menu;

public interface MenuMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Menu record);

	int insertSelective(Menu record);

	Menu selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Menu record);

	int updateByPrimaryKey(Menu record);

	// =========================================
	/**
	 * 查询系统所有菜单列表
	 * 
	 * @return
	 */
	List<Menu> queryByAll();

	/**
	 * 获取顶级菜单
	 * 
	 * @param map
	 * @return
	 */
	List<Menu> getRootMenu(Map map);

	/**
	 * 获取子菜单
	 * 
	 * @return
	 */
	List<Menu> getChildMenu();

	/**
	 * 根据权限id查询菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenuByRoleId(Integer roleId);

	/**
	 * 根据用户id查询父菜单菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<Menu> getRootMenuByUser(Integer userId);

	/**
	 * 根据用户id查询子菜单菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<Menu> getChildMenuByUser(Integer userId);
}