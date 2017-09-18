package net.yuanma.servlet.customer;

import java.io.IOException;
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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import net.yuanma.dto.CustomerDto;
import net.yuanma.dto.EmployeeDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.BusinessSource;
import net.yuanma.model.Customer;
import net.yuanma.model.WorkingField;
import net.yuanma.service.Employee.EmployeeI;
import net.yuanma.service.businesssource.BusinessSourceI;
import net.yuanma.service.customer.CustomerI;
import net.yuanma.service.workingfield.WorkingFieldI;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private CustomerI customerI = (CustomerI) ObjectFactory.getObject("customerI");
	private WorkingFieldI workingFieldI = (WorkingFieldI) ObjectFactory.getObject("workingFieldI");
	private BusinessSourceI businessSourceI = (BusinessSourceI) ObjectFactory.getObject("businessSourceI");
	private EmployeeI employeeI = (EmployeeI) ObjectFactory.getObject("EmployeeI");

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

	/*
	 * 新增
	 */
	protected void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		Integer fieldId = Integer.parseInt(request.getParameter("fieldId"));
		Integer sourceId = Integer.parseInt(request.getParameter("sourceId"));
		String customername = request.getParameter("customername");
		String postcode = request.getParameter("postcode");
		String tag = request.getParameter("tag");
		String remarks = request.getParameter("remarks");
		String busubessVolume = request.getParameter("busubessVolume");
		String employeeNumbers = request.getParameter("employeeNumbers");
		String address = request.getParameter("address");
		Integer linkManId = Integer.parseInt(request.getParameter("linkManId"));
		Customer c = new Customer();
		c.setPrinpical(linkManId);
		c.setFieldId(fieldId);
		c.setSourceId(sourceId);
		c.setCustomerName(customername);
		c.setPostCode(postcode);
		c.setTag(tag);
		c.setRemarks(remarks);
		c.setBusubessVolume(busubessVolume);
		c.setEmployeeNumbers(employeeNumbers);
		c.setAddress(address);
		JSONObject obj = new JSONObject();
		try {
			customerI.CustomerCheck(c);
			obj.put("flag", 1);
			obj.put("msg", "添加成功");
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("flag", 1);
			obj.put("msg", "添加成功");
			response.getWriter().write(obj.toString());
		}

	}

	/*
	 * 查询列表
	 */
	protected void getAllCustomer(HttpServletRequest request, HttpServletResponse response) {
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
			
			CustomerDto customerDto = new CustomerDto();
			
			try {
				if (request.getParameter("industryId") != null) {
					// 根据搜索所属行业
					Integer fieldId = Integer.parseInt(request.getParameter("industryId"));
					customerDto.setFieldId(fieldId);
				} else if (request.getParameter("customerName") != null) {
					String customerName = request.getParameter("customerName");
					customerDto.setCustomerName(customerName);
				} else if (request.getParameter("sourceId") != null) {
					Integer sourceId = Integer.parseInt(request.getParameter("sourceId"));
					customerDto.setSourceId(sourceId);
				} else if (request.getParameter("startTime") != null && request.getParameter("endTime") != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date startTime = dateFormat.parse(request.getParameter("startTime"));
					Date endTime = dateFormat.parse(request.getParameter("endTime"));
					customerDto.setStartTime(startTime);
					customerDto.setEndTime(endTime);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			PageInfo<CustomerDto> list = customerI.findAllByPage(customerDto, pageNum);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 添加页面
	 */
	protected void getAllToAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<WorkingField> list = workingFieldI.queryAll();
			List<BusinessSource> list2 = businessSourceI.queryAll();
			List<EmployeeDto> list3 = employeeI.queryAll();
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			System.out.println(list3);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/create.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 查询字典
	 */
	protected void getAllWorkingField(HttpServletRequest request, HttpServletResponse response) {
		// 所属行业
		response.setContentType("text/html;charset=utf-8");
		List<WorkingField> workingFields = workingFieldI.queryAll();
		JSONObject obj = new JSONObject();
		obj.put("workingFields", workingFields);
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 单个数据查询
	 */
	protected void selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			CustomerDto customerDtos = new CustomerDto();
			CustomerDto customerDto = customerI.selectById(customerId);
			List<CustomerDto> list = customerI.findAll(customerDtos);
			List<BusinessSource> list2 = businessSourceI.queryAll();
			List<EmployeeDto> list3 = employeeI.queryAll();
			List<WorkingField> list4 = workingFieldI.queryAll();
			request.setAttribute("customerDto", customerDto);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			request.setAttribute("list4", list4);

			request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/update.jsp").forward(request, response);

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 查看页面
	 */
	protected void selectByView(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			CustomerDto customerDto = new CustomerDto();

			customerDto = customerI.selectById(customerId);
			request.setAttribute("customerDto", customerDto);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/view.jsp").forward(request, response);

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 修改
	 */
	protected void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Integer customerId = Integer.parseInt(request.getParameter("customerId"));

			Integer fieldId = Integer.parseInt(request.getParameter("fieldId"));
			Integer sourceId = Integer.parseInt(request.getParameter("sourceId"));
			String customername = request.getParameter("customername");
			String postCode = request.getParameter("postCode");
			String tag = request.getParameter("tag");
			String remarks = request.getParameter("remarks");
			String busubessVolume = request.getParameter("busubessVolume");
			String employeeNumbers = request.getParameter("employeeNumbers");
			String address = request.getParameter("address");

			Integer linkManId = Integer.parseInt(request.getParameter("linkManId"));
			Customer c = new Customer();

			c.setUpdateTime(dateFormat.parse(dateFormat.format(new Date())));
			c.setPrinpical(linkManId);
			c.setFieldId(fieldId);
			c.setSourceId(sourceId);
			c.setCustomerName(customername);
			c.setPostCode(postCode);
			c.setTag(tag);
			c.setRemarks(remarks);
			c.setBusubessVolume(busubessVolume);
			c.setEmployeeNumbers(employeeNumbers);
			c.setCustomerId(customerId);
			c.setAddress(address);

			customerI.update(c);

			JSONObject obj = new JSONObject();
			obj.put("flag", 1);
			obj.put("msg", "添加成功");
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 搜索
	 */
//	@SuppressWarnings("unused")
//	protected void getSomeCustomer(HttpServletRequest request, HttpServletResponse response) throws ParseException {
//		try {
//			response.setContentType("text/html;charset=utf-8");
//			request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/index.jsp").forward(request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @throws ParseException
	 */
//	protected void getAllZd(HttpServletRequest request, HttpServletResponse response) throws ParseException {
//		List<CustomerDto> list = null;
//		CustomerDto customerDto = new CustomerDto();
//		try {
//			if (request.getParameter("industryId") != null) {
//				// 根据搜索所属行业
//				Integer fieldId = Integer.parseInt(request.getParameter("industryId"));
//				customerDto.setFieldId(fieldId);
//				list = customerI.findAll(customerDto);
//				request.setAttribute("list", list);
//			} else if (request.getParameter("customerName") != null) {
//				String customerName = request.getParameter("customerName");
//				customerDto.setCustomerName(customerName);
//				list = customerI.findAll(customerDto);
//				request.setAttribute("list", list);
//
//			} else if (request.getParameter("sourceId") != null) {
//				Integer sourceId = Integer.parseInt(request.getParameter("sourceId"));
//				// System.out.println(sourceId);
//				customerDto.setSourceId(sourceId);
//				list = customerI.findAll(customerDto);
//				request.setAttribute("list", list);
//			} else if (request.getParameter("startTime") != null && request.getParameter("endTime") != null) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				Date startTime = dateFormat.parse(request.getParameter("startTime"));
//				Date endTime = dateFormat.parse(request.getParameter("endTime"));
//				customerDto.setStartTime(startTime);
//				customerDto.setEndTime(endTime);
//				list = customerI.findAll(customerDto);
//				request.setAttribute("list", list);
//			}
//			request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/index.jsp").forward(request, response);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	/**
	 * 客戶來源查詢
	 * 
	 * @param request
	 * @param response
	 */
	protected void getAllBusinessSource(HttpServletRequest request, HttpServletResponse response) {
		// 所属行业
		response.setContentType("text/html;charset=utf-8");
		List<BusinessSource> businessSources = businessSourceI.queryAll();
		JSONObject obj = new JSONObject();
		obj.put("businessSources", businessSources);
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
