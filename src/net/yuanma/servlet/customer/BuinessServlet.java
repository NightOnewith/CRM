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

import net.yuanma.dto.AdvanceLogDto;
import net.yuanma.dto.BusinessDto;
import net.yuanma.dto.CustomerDto;
import net.yuanma.dto.EmployeeDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.Business;
import net.yuanma.model.BusinessSource;
import net.yuanma.model.BusinessType;
import net.yuanma.model.Employee;
import net.yuanma.model.LinkMain;
import net.yuanma.service.Employee.EmployeeI;
import net.yuanma.service.advancelog.AdvanceLogI;
import net.yuanma.service.business.BusinessI;
import net.yuanma.service.businesssource.BusinessSourceI;
import net.yuanma.service.businesstype.BusinessTypeI;
import net.yuanma.service.customer.CustomerI;
import net.yuanma.service.linkman.LinkManI;
import net.yuanma.service.workingfield.WorkingFieldI;

@WebServlet("/BuinessServlet")
public class BuinessServlet extends HttpServlet {
	private BusinessI businessI = (BusinessI) ObjectFactory.getObject("businessI");
	private CustomerI customerI = (CustomerI) ObjectFactory.getObject("customerI");
	private WorkingFieldI workingFieldI = (WorkingFieldI) ObjectFactory.getObject("workingFieldI");
	private BusinessSourceI businessSourceI = (BusinessSourceI) ObjectFactory.getObject("businessSourceI");
	private EmployeeI employeeI = (EmployeeI) ObjectFactory.getObject("EmployeeI");
	private LinkManI linkmanI = (LinkManI) ObjectFactory.getObject("linkmanI");
	private AdvanceLogI advanceLogI = (AdvanceLogI) ObjectFactory.getObject("advanceLogI");

	private BusinessTypeI businessTypeI = (BusinessTypeI) ObjectFactory.getObject("businessTypeI");

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
	protected void addBusiness(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			Integer linkMainId = Integer.parseInt(request.getParameter("linkMainId"));
			String businessName = request.getParameter("businessName");
			Integer businessTypeId = Integer.parseInt(request.getParameter("businessTypeId"));
			String status = request.getParameter("status");
			Integer businessSourceId = Integer.parseInt(request.getParameter("businessSourceId"));
			Long persalePrice = Long.parseLong(request.getParameter("persalePrice"));
			Short probability = Short.parseShort(request.getParameter("probability"));
			String visitContent = request.getParameter("visitContent");

			String employeeName = request.getParameter("employeeName");
			Employee employee = employeeI.findEmployeeByName(employeeName);

			Business b = new Business();

			String time = request.getParameter("visitTime");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date visitTime = dateFormat.parse(time);

			b.setEmployeeId(employee.getEmployeeId());
			b.setVisitTime(visitTime);
			b.setCustomerId(customerId);
			b.setLinkMainId(linkMainId);
			b.setBusinessName(businessName);
			b.setBusinessTypeId(businessTypeId);
			b.setStatus(status);
			b.setBusinessSourceId(businessSourceId);
			b.setPersalePrice(persalePrice);
			b.setProbability(probability);
			b.setVisitContent(visitContent);
			businessI.add(b);
			JSONObject obj = new JSONObject();
			obj.put("flag", 1);
			obj.put("msg", "添加成功");
			response.getWriter().write(obj.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 查询页面中所有
	 */
	protected void getAllBusiness(HttpServletRequest request, HttpServletResponse response) {
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
			
			BusinessDto businessDto = new BusinessDto();
			
			try {
				// 模糊查询商机名
				if (request.getParameter("businessName") != null) {
					String businessName = request.getParameter("businessName");
					businessDto.setBusinessName(businessName);
				} else if (request.getParameter("persalePrice") != null) {
					// 查询预计价格
					Long persalePrice = Long.parseLong(request.getParameter("persalePrice"));
					businessDto.setPersalePrice(persalePrice);
				} else if (request.getParameter("businessTypeId") != null) {
					// 查询商机类型
					Integer businessTypeId = Integer.parseInt(request.getParameter("businessTypeId"));
					// System.out.println(businessTypeId);
					businessDto.setBusinessTypeId(businessTypeId);
				} else if (request.getParameter("businessSourceId") != null) {
					// 商机来源查询
					Integer businessSourceId = Integer.parseInt(request.getParameter("businessSourceId"));
					businessDto.setBusinessSourceId(businessSourceId);
				} else if (request.getParameter("status") != null) {
					// 状态查询
					String status = request.getParameter("status");
					System.out.println(status);
					businessDto.setStatus(status);
				} else if (request.getParameter("startTime") != null && request.getParameter("endTime") != null) {
					// 联系时间
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date startTime = dateFormat.parse(request.getParameter("startTime"));
					Date endTime = dateFormat.parse(request.getParameter("endTime"));
					businessDto.setStartTime(startTime);
					businessDto.setEndTime(endTime);
				} else if (request.getParameter("openTime") != null && request.getParameter("overTime") != null) {
					// 修改时间
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date openTime = dateFormat.parse(request.getParameter("openTime"));
					Date overTime = dateFormat.parse(request.getParameter("overTime"));
					businessDto.setOpenTime(openTime);
					businessDto.setOverTime(overTime);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			PageInfo<BusinessDto> list = businessI.findAllByPage(businessDto, pageNum);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 查看
	 */
	protected void selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("type");
			if (!"1".equals(id) && id != "1") {
				Integer businessId = Integer.parseInt(request.getParameter("businessId"));
				CustomerDto customerDto = new CustomerDto();
				LinkMain linkMain = new LinkMain();
				List<EmployeeDto> employeeDtos = employeeI.queryAll();
				List<CustomerDto> customerDtos = customerI.findAll(customerDto);
				List<LinkMain> linkMains = linkmanI.findAll(linkMain);
				List<BusinessSource> businessSources = businessSourceI.queryAll();
				List<BusinessType> businessTypes = businessTypeI.queryAll();
				request.setAttribute("businessTypes", businessTypes);
				request.setAttribute("businessSources", businessSources);
				request.setAttribute("linkMains", linkMains);
				request.setAttribute("customerDtos", customerDtos);
				request.setAttribute("employeeDtos", employeeDtos);
				BusinessDto businessDto = new BusinessDto();
				businessDto = businessI.selectById(businessId);
				request.setAttribute("businessDto", businessDto);

				request.getRequestDispatcher("/WEB-INF/view/customerManage/business/update.jsp").forward(request,
						response);
				return;
			} else {
				Integer businessId = Integer.parseInt(request.getParameter("businessId"));
				BusinessDto businessDto = new BusinessDto();
				businessDto = businessI.selectById(businessId);
				AdvanceLogDto advanceLogDto = new AdvanceLogDto();
				Integer businessIds = Integer.parseInt(request.getParameter("businessId"));
				advanceLogDto.setBusinessId(businessIds);
				List<AdvanceLogDto> advanceLogDtos = advanceLogI.findAll(advanceLogDto);
				System.out.println(advanceLogDtos);
				request.setAttribute("advanceLogDtos", advanceLogDtos);
				request.setAttribute("acount", advanceLogDtos.size());
				request.setAttribute("businessDto", businessDto);
				request.getRequestDispatcher("/WEB-INF/view/customerManage/business/view.jsp").forward(request,
						response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 查询联系人
	 */
	protected void getAllLinkMan(HttpServletRequest request, HttpServletResponse response) {

		try {
			CustomerDto customerDto = new CustomerDto();
			LinkMain linkMain = new LinkMain();
			List<EmployeeDto> employeeDtos = employeeI.queryAll();
			List<CustomerDto> customerDtos = customerI.findAll(customerDto);
			List<LinkMain> linkMains = linkmanI.findAll(linkMain);
			List<BusinessSource> businessSources = businessSourceI.queryAll();
			List<BusinessType> businessTypes = businessTypeI.queryAll();
			request.setAttribute("businessTypes", businessTypes);
			request.setAttribute("businessSources", businessSources);
			request.setAttribute("linkMains", linkMains);
			request.setAttribute("customerDtos", customerDtos);
			request.setAttribute("employeeDtos", employeeDtos);

			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 修改
	 */
	protected void updateBuiness(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Integer businessId = Integer.parseInt(request.getParameter("businessId"));
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			Integer linkMainId = Integer.parseInt(request.getParameter("linkMainId"));
			String businessName = request.getParameter("businessName");
			Integer businessTypeId = Integer.parseInt(request.getParameter("businessTypeId"));
			String status = request.getParameter("status");
			Integer businessSourceId = Integer.parseInt(request.getParameter("businessSourceId"));
			Long persalePrice = Long.parseLong(request.getParameter("persalePrice"));
			Short probability = Short.parseShort(request.getParameter("probability"));
			String visitContent = request.getParameter("visitContent");
			Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));

			Business b = new Business();

			String time = request.getParameter("visitTime");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date visitTime = dateFormat.parse(time);
			b.setVisitTime(visitTime);

			b.setEmployeeId(employeeId);
			b.setBusinessId(businessId);
			b.setCustomerId(customerId);
			b.setLinkMainId(linkMainId);
			b.setBusinessName(businessName);
			b.setBusinessTypeId(businessTypeId);
			b.setStatus(status);
			b.setBusinessSourceId(businessSourceId);
			b.setPersalePrice(persalePrice);
			b.setProbability(probability);
			b.setVisitContent(visitContent);
			businessI.update(b);

			JSONObject obj = new JSONObject();
			obj.put("flag", 1);
			obj.put("msg", "添加成功");
			response.getWriter().write(obj.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 搜索
	 */
	protected void getSomeBuiness(HttpServletRequest request, HttpServletResponse response) {
		List<BusinessDto> list = null;
		try {
			// 模糊查询商机名
			if (request.getParameter("businessName") != null) {
				String businessName = request.getParameter("businessName");
				BusinessDto businessDto = new BusinessDto();
				businessDto.setBusinessName(businessName);
				list = businessI.findAll(businessDto);
				// request.setAttribute("list",list );
			} else if (request.getParameter("persalePrice") != null) {
				// 查询预计价格
				Long persalePrice = Long.parseLong(request.getParameter("persalePrice"));
				BusinessDto businessDto = new BusinessDto();
				businessDto.setPersalePrice(persalePrice);
				list = businessI.findAll(businessDto);
				// request.setAttribute("list",list );
			} else if (request.getParameter("businessTypeId") != null) {
				// 查询商机类型
				Integer businessTypeId = Integer.parseInt(request.getParameter("businessTypeId"));
				// System.out.println(businessTypeId);
				BusinessDto businessDto = new BusinessDto();
				businessDto.setBusinessTypeId(businessTypeId);
				list = businessI.findAll(businessDto);
				// System.out.println(list);
				// request.setAttribute("list", list);
			} else if (request.getParameter("businessSourceId") != null) {
				// 商机来源查询
				Integer businessSourceId = Integer.parseInt(request.getParameter("businessSourceId"));
				BusinessDto businessDto = new BusinessDto();
				businessDto.setBusinessSourceId(businessSourceId);
				list = businessI.findAll(businessDto);
				// request.setAttribute("list", list);
			} else if (request.getParameter("status") != null) {
				// 状态查询
				String status = request.getParameter("status");
				System.out.println(status);
				BusinessDto businessDto = new BusinessDto();
				businessDto.setStatus(status);
				list = businessI.findAll(businessDto);
				// request.setAttribute("list", list);
			} else if (request.getParameter("startTime") != null && request.getParameter("endTime") != null) {
				// 联系时间
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date startTime = dateFormat.parse(request.getParameter("startTime"));
				Date endTime = dateFormat.parse(request.getParameter("endTime"));
				System.out.println(startTime);
				System.out.println(endTime);
				BusinessDto businessDto = new BusinessDto();
				businessDto.setStartTime(startTime);
				businessDto.setEndTime(endTime);
				list = businessI.findAll(businessDto);
			} else if (request.getParameter("openTime") != null && request.getParameter("overTime") != null) {
				// 修改时间
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date openTime = dateFormat.parse(request.getParameter("openTime"));
				Date overTime = dateFormat.parse(request.getParameter("overTime"));
				BusinessDto businessDto = new BusinessDto();
				businessDto.setOpenTime(openTime);
				businessDto.setOverTime(overTime);
				list = businessI.findAll(businessDto);
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商机类型查询
	 * 
	 * @param request
	 * @param response
	 */
	protected void getAllType(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.setContentType("text/html;charset=utf-8");
			List<BusinessType> businessTypes = businessTypeI.queryAll();
			// for (BusinessType businessType : businessTypes) {
			// System.out.println(businessType);
			// }
			JSONObject obj = new JSONObject();
			obj.put("businessTypes", businessTypes);
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商机来源查询
	 * 
	 * @param request
	 * @param response
	 */
	protected void getAllSource(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.setContentType("text/html;charset=utf-8");
			List<BusinessSource> businessSources = businessSourceI.queryAll();
			// for (BusinessType businessType : businessTypes) {
			// System.out.println(businessType);
			// }
			JSONObject obj = new JSONObject();
			obj.put("businessSources", businessSources);
			response.getWriter().write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
