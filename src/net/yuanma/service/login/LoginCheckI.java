package net.yuanma.service.login;

import net.yuanma.model.LogIn;

public interface LoginCheckI {
	//登录校验
	LogIn LoginCheck(LogIn logIn);
	
	//更新密码
	void updateLogin(LogIn logIn);
	
	//添加账户
	void addLogin(LogIn logIn);
	
}
