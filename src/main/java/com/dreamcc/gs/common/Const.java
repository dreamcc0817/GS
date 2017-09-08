package com.dreamcc.gs.common;

import com.google.common.collect.Sets;

import java.util.Set;
/**
 * 
 * @description 常量 
 * @author 刘畅
 * @date 2017年9月2日
 *
 */
public class Const {

	public static final String CURRENT_USER = "currentUser";

	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

	public interface ProductListOrderBy {
		Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
	}

	public interface Role {
		int ROLE_CUSTOMER = 0; // 普通用户
		int ROLE_ADMIN = 1;// 管理员
	}

}