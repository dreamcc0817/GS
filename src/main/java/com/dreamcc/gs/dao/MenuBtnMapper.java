package com.dreamcc.gs.dao;

import java.util.List;

import com.dreamcc.gs.bean.MenuBtn;

public interface MenuBtnMapper<T> extends BaseMapper<T> {

	public List<T> queryByMenuid(Integer menuid);

	public List<T> queryByMenuUrl(String url);

	public void deleteByMenuid(Integer menuid);

	public List<T> getMenuBtnByUser(Integer userid);

	public List<T> queryByAll();
}
