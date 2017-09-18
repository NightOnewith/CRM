package net.yuanma.servlet.homePage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;

import net.yuanma.dto.CustomerBusinessDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.Employee;
import net.yuanma.model.Task;
import net.yuanma.service.business.BusinessI;
import net.yuanma.service.customer.CustomerI;
import net.yuanma.service.task.TaskI;

@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private BusinessI businessI = (BusinessI) ObjectFactory.getObject("businessI");
	private TaskI taskI = (TaskI) ObjectFactory.getObject("taskI");
	private CustomerI customerI = (CustomerI) ObjectFactory.getObject("customerI");

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
	 * 进入主页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void countAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			response.setContentType("text/html;charset=utf-8");

			// 分页中的东西
			int pageNum;
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr != null) {
				pageNum = Integer.parseInt(pageNoStr);
			} else {
				pageNum = 1;
			}

			// 查询所有客户商机
			PageInfo<CustomerBusinessDto> cbDtos = businessI.findAllBC(pageNum);
			request.setAttribute("cbDtos", cbDtos);

			// 查询我的任务
			HttpSession session = request.getSession();
			Employee employee = (Employee) session.getAttribute("currentUser");
			PageInfo<Task> myTasks = taskI.getMyTask(employee.getEmployeeId(), 1);
			request.setAttribute("myTasks", myTasks);

			// 近七日新增客户
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cd = Calendar.getInstance();
			cd.set(Calendar.DAY_OF_YEAR, cd.get(Calendar.DAY_OF_YEAR) - 7);
			Date monday = cd.getTime();
			Date today = new Date();
			String weekTime = sdf.format(monday);
			String todayTime = sdf.format(today);
			int count = customerI.findByDate(weekTime, todayTime);
			request.setAttribute("count", count);

			// 月度新增客户 2017-09-01 00:00:00 ~ 2017 10-01 00:00:00
			// 获取当月第一天和最后一天  
			Calendar cal = Calendar.getInstance(); 
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	        // 获取前月的第一天  
	        cal = Calendar.getInstance();  
	        cal.add(Calendar.MONTH, 0);  
	        cal.set(Calendar.DAY_OF_MONTH, 1);  
	        String firstday = format.format(cal.getTime());  
	        // 获取前月的最后一天  
	        cal = Calendar.getInstance();  
	        cal.add(Calendar.MONTH, 1);  
	        cal.set(Calendar.DAY_OF_MONTH, 1);  
	        String lastday = format.format(cal.getTime()); 
	        int count2 = customerI.findByDate(firstday, lastday);
	        request.setAttribute("count2", count2);
	        
	        //近七日新增商机
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 7);
			Date monday2 = c.getTime();
			Date today2 = new Date();
			String weekTime2 = sd.format(monday2);
			String todayTime2 = sd.format(today2);
			int count3 = businessI.findByDate(weekTime2, todayTime2);
			request.setAttribute("count3", count3);

			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
