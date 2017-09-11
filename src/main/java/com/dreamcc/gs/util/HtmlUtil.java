package com.dreamcc.gs.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

/**
 * 
 * @description Html连接类
 * @author 刘畅
 * @date 2017年9月7日
 *
 */
public class HtmlUtil {
	
	public static void writerJson(HttpServletResponse response,String jsonStr){
		writer(response,jsonStr);
	}
	
	public static void writerJson(HttpServletResponse response,Object object){
		try {
			response.setContentType("application/json");
			writer(response,JSONUtil.toJSONString(object));
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}
	public static void writerHtml(HttpServletResponse response,String htmlStr) {
		writer(response,htmlStr);
	}
	
	private static void writer(HttpServletResponse response,String str){
		try {
			StringBuffer result = new StringBuffer();
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out= null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
