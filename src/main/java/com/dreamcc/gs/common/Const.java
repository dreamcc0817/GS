package com.dreamcc.gs.common;

import java.util.ResourceBundle;
import java.util.Set;

import com.google.common.collect.Sets;
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
	private static ResourceBundle res = ResourceBundle.getBundle("sysconfig");
	//网站名称
	public static String SSI_WEBSITE_NAME = res.getString("ssi.website.name");
	//网站域名
	public static String SSI_WEBSITE_DOMAIN = res.getString("ssi.website.domain");
	//网站地址
	public static String SSI_WEBSITE_URL = res.getString("ssi.website.url");
	
	
	//项目根路径 线上是tomcat的，本地可以配成工作空间项目
	public static String WORK_ROOT_PATH  = res.getString("work.root.path");
	//模板根目录
	public static String WORK_TEMPLATE_PATH  = res.getString("work.template.path");
	
	//上传根目录地址 http://image.mn606.com/
	public static String UPLOAD_URL = res.getString("upload.url");
	//搜索的域名
	public static String SEARCH_URL =  res.getString("search.url");
	
	//lookmn的域名
	public static String LOOK_URL =  res.getString("look.url");
	
	//搜索图片过滤分数0.0-.1.0之间的小数
	public static String SEARCH_SCORE =  res.getString("search.score");
	
	public static String UPLOAD_PATH = res.getString("upload.path");
	
	public static String UPLOAD_PIC_SIZE = res.getString("upload.pic.size");
	
	//所以文件存放根目录
	public static String INDEX_BASE_DIR =res.getString("index.base.dir");
	
	

	/**
	 * 超级管理员常量
	 * @author lu
	 *
	 */
	public static enum SuperAdmin {
		NO(0, "否"), YES(1,"是");
		public int key;
		public String value;
		private SuperAdmin(int key, String value) {
			this.key = key;
			this.value = value;
		}
		public static SuperAdmin get(int key) {
			SuperAdmin[] values = SuperAdmin.values();
			for (SuperAdmin object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

}
