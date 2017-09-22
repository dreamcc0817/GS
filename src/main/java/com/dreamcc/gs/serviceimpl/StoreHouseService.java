package com.dreamcc.gs.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.dao.StoreHouseMapper;

@Service("storeHouse")
public class StoreHouseService<T> extends BaseService<T> {

	private final static Logger log = Logger.getLogger(StoreHouseService.class);
	@Autowired
	private StoreHouseMapper<T> mapper;

	public StoreHouseMapper<T> getMapper() {
		return mapper;
	}
}
