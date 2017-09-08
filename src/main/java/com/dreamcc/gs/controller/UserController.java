package com.dreamcc.gs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamcc.gs.bean.User;
import com.dreamcc.gs.common.Const;
import com.dreamcc.gs.common.ServerResponse;
import com.dreamcc.gs.service.IUserService;

/**
 * 
 * @description 用户控制类
 * @author 刘畅
 * @date 2017年9月2日
 *
 */
@Controller
@RequestMapping
public class UserController {
	
	@Autowired
	private IUserService iUserService;
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="toLogin",method=RequestMethod.POST)
	@ResponseBody
    public ServerResponse<User> login(String username,String password,HttpSession session){
    	ServerResponse<User> response = iUserService.login(username, password);
    	if(response.isSuccess()){
    		session.setAttribute(Const.CURRENT_USER, response.getData());
    	}
    	return response;
	}
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> register(User user){
		return iUserService.register(user);
	}
}
