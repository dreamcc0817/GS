package com.dreamcc.gs.dao;

import com.dreamcc.gs.model.SysUserModel;

public interface UserMapper<T> extends BaseMapper<T> {

	/**
	 * 检查登录
	 * 
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(SysUserModel model);

	/**
	 * 查询邮箱总数，检查是否存在
	 * 
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);

}