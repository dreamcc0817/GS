package com.dreamcc.gs.serviceimpl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.bean.Role;
import com.dreamcc.gs.bean.RoleRel;
import com.dreamcc.gs.bean.RoleRel.RelType;
import com.dreamcc.gs.dao.BaseMapper;
import com.dreamcc.gs.dao.RoleMapper;
import com.dreamcc.gs.dao.RoleRelMapper;

@Service("roleService")
public class RoleService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(RoleService.class);

	@Autowired(required = false)
	private RoleRelService<RoleRel> sysRoleRelService;

	@Autowired(required = false)
	private RoleMapper<T> mapper;

	public RoleMapper<T> getMapper() {
		return mapper;
	}
	/**
	 * 添加角色&菜单关系
	 * @param roleId
	 * @param menuIds
	 * @throws Exception
	 */
	public void addRoleMenuRel(Integer roleId, Integer[] menuIds) throws Exception {
		if (roleId == null || menuIds == null || menuIds.length < 1) {
			return;
		}
		for (Integer menuid : menuIds) {
			RoleRel rel = new RoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(menuid);
			rel.setRelType(RelType.MENU.key);
			sysRoleRelService.add(rel);
		}
	}

	/**
	 * 添加角色&菜单按钮关系
	 */
	public void addRoleBtnRel(Integer roleId, Integer[] btnIds) throws Exception {
		if (roleId == null || btnIds == null || btnIds.length < 1) {
			return;
		}
		for (Integer btnid : btnIds) {
			RoleRel rel = new RoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(btnid);
			rel.setRelType(RelType.BTN.key);
			sysRoleRelService.add(rel);
		}
	}

	/**
	 * 添加
	 * 
	 * @param role
	 * @param menuIds
	 * @param btnIds
	 * @throws Exception
	 */
	public void add(Role role, Integer[] menuIds, Integer[] btnIds) throws Exception {
		super.add((T) role);
		addRoleMenuRel(role.getId(), menuIds);
		addRoleBtnRel(role.getId(),btnIds);
	}

	/**
	 * 更新
	 * 
	 * @param role
	 * @param menuIds
	 * @param btnIds
	 * @throws Exception
	 */
	public void update(Role role, Integer[] menuIds, Integer[] btnIds) throws Exception {
		super.update((T)role);
		//清除关联关系
		sysRoleRelService.deleteByRoleId(role.getId(),RelType.MENU.key);
		sysRoleRelService.deleteByRoleId(role.getId(),RelType.BTN.key);
		addRoleMenuRel(role.getId(),menuIds);
		addRoleBtnRel(role.getId(),btnIds);
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @throws Exception 
	 */
	public void delete(Integer[] ids) throws Exception {
		super.delete(ids);
		for(Integer id : ids){
			//清除关联关系
			sysRoleRelService.deleteByRoleId(id);
		}
	}

	/**
	 * 查询全部有效权限
	 * 
	 * @return
	 */
	public List<T> queryAllList() {
		return getMapper().queryAllList();
	}

	/**
	 * 查询用户所有权限
	 * 
	 * @param userid
	 * @return
	 */
	public List<T> queryByUserId(Integer userid) {
		return getMapper().queryByUserid(userid);
	}
}
