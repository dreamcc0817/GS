package com.dreamcc.gs.serviceimpl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.bean.Param;
import com.dreamcc.gs.dao.ParamMapper;

@Service("paramService")
public class ParamService<T> extends BaseService<T>{
	private final static Logger log = Logger.getLogger(ParamService.class);

	@Autowired
	private ParamMapper<T> mapper;

	public ParamMapper<T> getMapper() {
		return mapper;
	}
	/**
	 * 通过参数类型,参数显示名称查询
	 * @param paramName
	 * @return
	 */
	public Param getParamByName(String paramName){
		return mapper.getParamByName(paramName);
	}
}
