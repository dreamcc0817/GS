package com.dreamcc.gs.dao;

import com.dreamcc.gs.bean.MenuBtn;

public interface MenuBtnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuBtn record);

    int insertSelective(MenuBtn record);

    MenuBtn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuBtn record);

    int updateByPrimaryKey(MenuBtn record);
}