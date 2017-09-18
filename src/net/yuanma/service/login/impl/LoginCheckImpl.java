package net.yuanma.service.login.impl;

import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.LogInMapper;
import net.yuanma.model.LogIn;
import net.yuanma.service.login.LoginCheckI;
import net.yuanma.util.MD5Util;

public class LoginCheckImpl implements LoginCheckI {

	@Override
	public LogIn LoginCheck(LogIn logIn) {
		LogInMapper logInMapper = (LogInMapper) ObjectFactory.getObject("logInMapper");
		LogIn logInNew=logInMapper.selectByLogIn(logIn.getEmployeeId(),MD5Util.md5(logIn.getEmmPassword()));
		return logInNew;
	}

	@Override
	public void updateLogin(LogIn logIn) {
		LogInMapper logInMapper = (LogInMapper) ObjectFactory.getObject("logInMapper");
		logInMapper.updateByPrimaryKey(logIn);
	}

	@Override
	public void addLogin(LogIn logIn) {
		LogInMapper logInMapper = (LogInMapper) ObjectFactory.getObject("logInMapper");
		logInMapper.insert(logIn);
	}

}
