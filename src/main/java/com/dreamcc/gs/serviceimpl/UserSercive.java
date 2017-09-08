package com.dreamcc.gs.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcc.gs.bean.User;
import com.dreamcc.gs.common.Const;
import com.dreamcc.gs.common.ServerResponse;
import com.dreamcc.gs.dao.UserMapper;
import com.dreamcc.gs.service.IUserService;
import com.dreamcc.gs.util.MD5Util;

@Service
public class UserSercive implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public ServerResponse<User> login(String username, String password) {
		int resultCount = userMapper.checkUsername(username);
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("用户名不存在");
		}
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		User user = userMapper.selectLogin(username, md5Password);
		if (user == null) {
			return ServerResponse.createByErrorMessage("密码错误");
		}
		user.setPwd(org.apache.commons.lang3.StringUtils.EMPTY);
		return ServerResponse.createBySuccess("登录成功", user);
	}

	@Override
	public ServerResponse<String> register(User user) {
		ServerResponse validResponse = this.checkValid(user.getEmail(), Const.USERNAME);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		user.setPwd(MD5Util.MD5EncodeUtf8(user.getPwd()));
		int resultCount = userMapper.insert(user);
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("注册失败");
		}
		return ServerResponse.createBySuccessMessage("注册成功");
	}

	@Override
	public ServerResponse<String> checkValid(String str, String type) {
		if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
			// 开始校验
			if (Const.USERNAME.equals(type)) {
				int resultCount = userMapper.checkUsername(str);
				if (resultCount > 0) {
					return ServerResponse.createByErrorMessage("用户名已存在");
				}
			}
			if (Const.EMAIL.equals(type)) {
				int resultCount = userMapper.checkEmail(str);
				if (resultCount > 0) {
					return ServerResponse.createByErrorMessage("email已存在");
				}
			}
		} else {
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccessMessage("校验成功");
	}

	@Override
	public ServerResponse selectQuestion(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<String> checkAnswer(String username, String question, String answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<User> updateInformation(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<User> getInformation(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse checkAdminRole(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
