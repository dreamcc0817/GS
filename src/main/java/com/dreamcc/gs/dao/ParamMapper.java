package com.dreamcc.gs.dao;

import com.dreamcc.gs.bean.Param;

public interface ParamMapper<T> extends BaseMapper<T> {
	
	/**
	 * 通过参数类型,显示名称查找
	 * @param paramName
	 * @return
	 */
	Param getParamByName(String paramName);

}