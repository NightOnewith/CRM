package net.yuanma.servlet.login;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import net.yuanma.dto.MenuDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.Employee;
import net.yuanma.model.LogIn;
import net.yuanma.service.Employee.EmployeeI;
import net.yuanma.service.login.LoginCheckI;
import net.yuanma.service.menu.MenuI;
import net.yuanma.util.MD5Util;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private LoginCheckI loginI = (LoginCheckI) ObjectFactory.getObject("loginI");
	private EmployeeI employeeI = (EmployeeI) ObjectFactory.getObject("EmployeeI");
	private MenuI menuI = (MenuI) ObjectFactory.getObject("menuI");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 登录检查
	 * 
	 * @param request
	 * @param response
	 */
	protected void loginCheck(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		// 获得用户输入的对象
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String password = request.getParameter("password");
		
		//检查用户状态
		Employee employee = employeeI.findEmployeeById(employeeId);
		
		LogIn login = new LogIn(employeeId, password);
		// 与数据库进行判断是否存在该用户
		JSONObject json = new JSONObject();
		if (null != loginI.LoginCheck(login)) {
			if (employee.getStatus().equals("0")) {
				json.put("status", "1");
			} else {
				json.put("status", "0");
				json.put("msg", "该用户已被禁用");
			}
		} else {
			json.put("status", "0");
			json.put("msg", "工号或密码错误");
		}
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 登录主页
	 * 
	 * @param request
	 * @param response
	 */
	protected void loginToCms(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获得用户输入的账户信息
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String password = request.getParameter("password");
			LogIn logIn = new LogIn(employeeId, password);
			// 检验密码是否是123456
			if (loginI.LoginCheck(logIn) != null) {
				if (password.equals("123456")) { // 密码为123456 跳转到修改账户界面
					response.sendRedirect(request.getContextPath() + "/updatepass.jsp");
				} else { // 否则进入主界面
					// 设置session
					HttpSession session = request.getSession();
					Employee employee = (Employee) session.getAttribute("currentUser");
					if (employee == null) {
						employee = new Employee();
					}
					employee = employeeI.findEmployeeById(employeeId);
					session.setAttribute("currentUser", employee);
					
					//查询员工对应菜单
					List<MenuDto> menus = menuI.getAllMenuInfo(employeeId);
					session.setAttribute("menus", menus);
					
					session.setMaxInactiveInterval(365*24*60);
					response.sendRedirect(request.getContextPath() + "/ControlServlet?method=countAll");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updatePassword(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		// 获得用户输入的对象信息
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String newpassword = request.getParameter("newpassword");
		String oldpassword = request.getParameter("oldpassword");
		LogIn login = new LogIn(employeeId, oldpassword);
		LogIn loginNew = loginI.LoginCheck(login);
		JSONObject json = new JSONObject();
		if (loginNew != null) {
			login.setUserId(loginNew.getUserId());
			login.setEmmPassword(MD5Util.md5(newpassword));
			loginI.updateLogin(login);
			json.put("status", "1");
			json.put("msg", "修改成功");
		} else {
			json.put("status", "0");
			json.put("msg", "原密码错误，修改失败");
		}
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
