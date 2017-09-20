package com.dreamcc.gs.serviceimpl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.dao.SupplierMapper;

@Service("supplierService")
public class SupplierService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(SupplierService.class);

	@Autowired
	private SupplierMapper<T> mapper;

	public SupplierMapper<T> getMapper() {
		return mapper;
	}
	
	public int getSupplierCountByName(String supName){
		return getMapper().getSupplierCountByName(supName);
	}
}
