package com.dreamcc.gs.serviceimpl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.dao.MenuBtnMapper;

@Service("menuBtnService")
public class MenuBtnService<T> extends BaseService<T> {

	private final static Logger log = Logger.getLogger(MenuBtnService.class);

	@Autowired
	private MenuBtnMapper<T> mapper;

	public MenuBtnMapper<T> getMapper() {
		return mapper;
	}

	public List<T> queryAll() {
		return getMapper().queryByAll();
	}

	public List<T> queryByMenuid(Integer menuid) {
		return getMapper().queryByMenuid(menuid);
	}

	public List<T> queryByMenuUrl(String url) {
		return getMapper().queryByMenuUrl(url);
	}

	public void deleteByMenuid(Integer menuid) {
		getMapper().deleteByMenuid(menuid);
	}

	public List<T> getMenuBtnByUser(Integer userid) {
		return getMapper().getMenuBtnByUser(userid);
	}
}
