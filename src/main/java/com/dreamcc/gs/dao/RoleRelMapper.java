package com.dreamcc.gs.dao;

import java.util.List;

import com.dreamcc.gs.bean.RoleRel;

public interface RoleRelMapper<T> extends BaseMapper<T> {

	public void deleteByRoleId(java.util.Map<String, Object> param);

	public void deleteByObjId(java.util.Map<String, Object> param);

	public List<RoleRel> queryByRoleId(java.util.Map<String, Object> param);

	public List<RoleRel> queryByObjId(java.util.Map<String, Object> param);

}
