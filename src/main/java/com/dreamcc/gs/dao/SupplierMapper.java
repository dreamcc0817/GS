package com.dreamcc.gs.dao;

public interface SupplierMapper<T> extends BaseMapper<T> {

	public int getSupplierCountByName(String supName);
	
}