package com.dreamcc.gs.dao;

import com.dreamcc.gs.bean.Main;

public interface MainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Main record);

    int insertSelective(Main record);

    Main selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Main record);

    int updateByPrimaryKey(Main record);
}