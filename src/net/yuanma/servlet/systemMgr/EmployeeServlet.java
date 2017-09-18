package net.yuanma.servlet.systemMgr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.StatusVal;
import net.yuanma.dto.EmployeeDtoZj;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.Department;
import net.yuanma.model.EmmPosition;
import net.yuanma.model.Employee;
import net.yuanma.model.LogIn;
import net.yuanma.service.Department.DepartmentI;
import net.yuanma.service.EmmPosition.EmmPositionI;
import net.yuanma.service.Employee.EmployeeI;
import net.yuanma.service.login.LoginCheckI;
import net.yuanma.util.MD5Util;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private LoginCheckI loginI = (LoginCheckI) ObjectFactory.getObject("loginI");
	private EmployeeI employeeI = (EmployeeI) ObjectFactory.getObject("EmployeeI");
	private DepartmentI departmentI = (DepartmentI) ObjectFactory.getObject("DepartmentI");
	private EmmPositionI emmPositionI = (EmmPositionI) ObjectFactory.getObject("EmmPositionI");

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
	 * 获取员工列表
	 * 
	 * @param request
	 * @param response
	 */
	protected void getAllEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			
			//分页中的东西
			int pageNum;
			String pageNoStr=request.getParameter("pageNo");
			if (pageNoStr!=null) {
				pageNum=Integer.parseInt(pageNoStr);
			}else{
				pageNum=1;
			}
			
			String employeeId = request.getParameter("employeeId");
			String employeeName = request.getParameter("employeeName");
			
			EmployeeDtoZj employeeDto = new EmployeeDtoZj();
			if (employeeId != null && !employeeId.equals("")) {
				employeeDto.setEmployeeId(Integer.parseInt(employeeId));
				request.setAttribute("employeeId", employeeId);
			} 
			if (employeeName != null && !employeeName.equals("")) {
				employeeDto.setEmployeeName(employeeName);
				request.setAttribute("employeeName", employeeName);
			}
			PageInfo<EmployeeDtoZj> employeeDtos = employeeI.findAllEmployeePage(employeeDto, pageNum);
			
			request.setAttribute("employeeDtos", employeeDtos);
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/manage/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询单个员工信息传入修改界面
	 * 
	 * @param request
	 * @param response
	 */
	protected void findEmployeeById(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			// 查询所有部门
			List<Department> departments = departmentI.findAllDepartment();
			request.setAttribute("departments", departments);
			// 查询所有职位
			EmmPosition emmPosition = new EmmPosition();
			List<EmmPosition> emmPositions = emmPositionI.findAllEmmPosition(emmPosition);
			request.setAttribute("emmPositions", emmPositions);

			// 查询单个员工
			EmployeeDtoZj employeeDto = new EmployeeDtoZj();
			employeeDto.setEmployeeId(employeeId);
			List<EmployeeDtoZj> employee = employeeI.findAllEmployee(employeeDto);
			request.setAttribute("employee", employee.get(0));

			request.getRequestDispatcher("/WEB-INF/view/systemSettings/manage/update.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取部门、职位传入添加员工界面
	 * 
	 * @param request
	 * @param response
	 */
	protected void getDeapartAndPostionInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			// 查询所有部门
			List<Department> departments = departmentI.findAllDepartment();
			request.setAttribute("departments", departments);
			// 查询所有职位
			EmmPosition emmPosition = new EmmPosition();
			List<EmmPosition> emmPositions = emmPositionI.findAllEmmPosition(emmPosition);
			request.setAttribute("emmPositions", emmPositions);
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/manage/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 检查上级员工是否存在
	 */
	protected void checkParentEmployee(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = new JSONObject();
		try {
			int parentemployeeId = Integer.parseInt(request.getParameter("parentemployeeId"));
			EmployeeDtoZj employeeDto = new EmployeeDtoZj();
			employeeDto.setEmployeeId(parentemployeeId);
			List<EmployeeDtoZj> employees = employeeI.findAllEmployee(employeeDto);
			if (employees.size() > 0) { // 存在该上级员工
				json.put("status", "1");
			} else { // 不存在该上级员工
				json.put("status", "0");
				json.put("msg", "不存在该上级员工");
			}
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加员工(employee表和log_in表)
	 * 
	 * @param request
	 * @param response
	 */
	protected void addEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			// 从页面取值
			String employeeName = request.getParameter("manage-name");
			int departmentId = Integer.parseInt(request.getParameter("manage-department_id"));
			int positionId = Integer.parseInt(request.getParameter("manage-position_id"));
			String phone = request.getParameter("manage-mobile");
			String email = request.getParameter("manage-email");
			String status = StatusVal.NORMAL;
			int parentemployeeId = Integer.parseInt(request.getParameter("parentemployeeId"));
			String password = StatusVal.PASSWORD;
			
			// 添加employee表
			Employee employee = new Employee();
			employee.setEmployeeName(employeeName);
			employee.setDepartmentId(departmentId);
			employee.setPositonId(positionId);
			employee.setPhone(phone);
			employee.setEmail(email);
			employee.setStatus(status);
			employee.setParentEmployeeId(parentemployeeId);
			employeeI.addEmployee(employee);
			int employeeId = employee.getEmployeeId();

			// 添加log_in表
			LogIn login = new LogIn();
			login.setEmployeeId(employeeId);
			login.setEmmPassword(MD5Util.md5(password));
			loginI.addLogin(login);

			response.sendRedirect(request.getContextPath() + "/EmployeeServlet?method=getAllEmployee");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新员工信息
	 * 
	 * @param request
	 * @param response
	 */
	protected void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("manage-name");
			int departmentId = Integer.parseInt(request.getParameter("manage-department_id"));
			int positionId = Integer.parseInt(request.getParameter("manage-position_id"));
			String phone = request.getParameter("manage-mobile");
			String email = request.getParameter("manage-email");
			int parentemployeeId = Integer.parseInt(request.getParameter("parentemployeeId"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setEmployeeName(employeeName);
			employee.setDepartmentId(departmentId);
			employee.setPositonId(positionId);
			employee.setPhone(phone);
			employee.setEmail(email);
			employee.setParentEmployeeId(parentemployeeId);
			try {
				employee.setUpdateTime(dateFormat.parse(dateFormat.format(new Date())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			employeeI.updateEmployee(employee);
			
			response.sendRedirect(request.getContextPath() + "/EmployeeServlet?method=getAllEmployee");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 切换员工状态
	 * 
	 * @param request
	 * @param response
	 */
	protected void updateEmployeeStatus(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String status = request.getParameter("status");
			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setStatus(status);
			employeeI.updateEmployee(employee);
			response.sendRedirect(request.getContextPath() + "/EmployeeServlet?method=getAllEmployee");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
