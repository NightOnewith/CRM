package net.yuanma.servlet.customer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import net.yuanma.dto.BusinessDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.AdvanceLog;
import net.yuanma.model.Business;
import net.yuanma.service.advancelog.AdvanceLogI;
import net.yuanma.service.business.BusinessI;

@WebServlet("/AdvanceLogServlet")
public class AdvanceLogServlet extends HttpServlet {
	private AdvanceLogI advanceLogI = (AdvanceLogI) ObjectFactory.getObject("advanceLogI");
	private BusinessI businessI = (BusinessI) ObjectFactory.getObject("businessI");

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

	protected void selectBuines(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Integer businessId = Integer.parseInt(request.getParameter("businessId"));
			response.setContentType("text/html;charset=utf-8");
			BusinessDto businessDto = new BusinessDto();
			businessDto = businessI.selectById(businessId);
			request.setAttribute("businessDto", businessDto);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/createTj.jsp").forward(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 新增
	 */
	protected void addAdvanceLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		// 添加推进
		Integer businessId = Integer.parseInt(request.getParameter("businessId"));
		Integer creator = Integer.parseInt(request.getParameter("creator"));
		String advanceContent = request.getParameter("advanceContent");
		String advanceStatus = request.getParameter("advanceStatus");

		AdvanceLog a = new AdvanceLog();
		a.setAdvanceContent(advanceContent);
		a.setBusinessId(businessId);
		a.setCreator(creator);
		a.setAdvanceStatus(advanceStatus);
		advanceLogI.add(a);
		// 修改商机表

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String status = request.getParameter("advanceStatus");
			// System.out.println(status + "-----------");
			String visitContent = request.getParameter("description");
			// System.out.println(visitContent + "------------");
			Date visitTime = dateFormat.parse(request.getParameter("visitTime"));
			// Date visitTime =
			// dateFormat.parse(dateFormat.format(request.getParameter("visitTime")));
			// System.out.println(visitTime + "------------");
			Business business = new Business();
			business.setStatus(status);
			business.setVisitContent(visitContent);
			business.setBusinessId(businessId);
			business.setVisitTime(visitTime);
			businessI.update(business);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject obj = new JSONObject();
		obj.put("flag", 1);
		obj.put("msg", "添加成功");
		response.getWriter().write(obj.toString());
	}

	/*
	 * 查询
	 */
	protected void getAllAdvanceLog(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.setContentType("text/html;charset=utf-8");

			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/view.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 删除
	 */
	protected void deleteAdvanceLog(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.sendRedirect(request.getContextPath() + "/AdvanceLogServlet?method=getAllAdvanceLog");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
