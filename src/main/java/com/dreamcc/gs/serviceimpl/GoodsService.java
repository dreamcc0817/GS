package com.dreamcc.gs.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.dao.GoodsMapper;
@Service("goodsService")
public class GoodsService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(GoodsService.class);
	@Autowired
	private GoodsMapper<T> mapper;
	
	public GoodsMapper<T> getMapper(){
		return mapper;
	}
}
