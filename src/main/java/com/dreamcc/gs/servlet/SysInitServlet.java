package com.dreamcc.gs.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dreamcc.gs.bean.Param;
import com.dreamcc.gs.bean.Supplier;
import com.dreamcc.gs.model.ParamModel;
import com.dreamcc.gs.serviceimpl.ParamService;
/**
 * 
 * @description 加载系统参数 
 * @author 刘畅
 * @date 2017年9月22日
 *
 */
public class SysInitServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(SysInitServlet.class);
	@Autowired
	private ParamService<Param> paramServive;
	
	public void init() throws ServletException{
		//定义类型
		Map<String,Object> sysParam = new HashMap<String,Object>();
		//定义具体参数
		ParamModel paramModel = new ParamModel();
		//从服务端取出全部系统参数	
		try {
			List<Param> param = paramServive.queryByList(paramModel);
			//循环把系统参数放到map中
			for(int i = 0;i < param.size();i++){
				if(i==0){

				}
				//判断参数类型是否一致
				if(param.get(i) == param.get(i-1)){
					
				}else{
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
