package net.yuanma.servlet.customer;

import java.io.IOException;
import java.lang.reflect.Method;
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
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.LinkMain;
import net.yuanma.service.customer.CustomerI;
import net.yuanma.service.linkman.LinkManI;

@WebServlet("/LinkManServlet")
public class LinkManServlet extends HttpServlet {
	private LinkManI linkmanI = (LinkManI) ObjectFactory.getObject("linkmanI");
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

	/*
	 * 新增
	 */
	protected void addLinkMan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String nickname = request.getParameter("nickname");
		String position = request.getParameter("position");
		String phonenum = request.getParameter("phonenum");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		String remarks = request.getParameter("remarks");
		LinkMain linkMain = new LinkMain();
		linkMain.setName(name);
		linkMain.setSex(sex);
		linkMain.setNickname(nickname);
		linkMain.setPosition(position);
		linkMain.setPhonenum(phonenum);
		linkMain.setEmail(email);
		linkMain.setQq(qq);
		linkMain.setRemarks(remarks);
		linkmanI.add(linkMain);

		JSONObject obj = new JSONObject();
		obj.put("flag", 1);
		obj.put("msg", "添加成功");
		response.getWriter().write(obj.toString());
	}

	/*
	 * 查询
	 */
	protected void getAllLinkMan(HttpServletRequest request, HttpServletResponse response) {
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

			LinkMain linkMain = new LinkMain();
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			if (mobile != null && mobile != "") {
				linkMain.setPhonenum(mobile);
			}
			if (email != null && email != "") {
				linkMain.setEmail(email);
			}
			PageInfo<LinkMain> list = linkmanI.findAllByPage(linkMain, pageNum);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/index.jsp").forward(request, response);
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
				CustomerDto customerDto = new CustomerDto();
				Integer linkMainId = Integer.parseInt(request.getParameter("linkMainId"));
				LinkMain linkMain = linkmanI.selectById(linkMainId);
				List<CustomerDto> list2 = customerI.findAll(customerDto);
				request.setAttribute("list2", list2);
				request.setAttribute("linkMain", linkMain);
				request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/update.jsp").forward(request,
						response);
				return;
			} else {
				Integer linkMainId = Integer.parseInt(request.getParameter("linkMainId"));
				LinkMain linkMain = new LinkMain();
				linkMain = linkmanI.selectById(linkMainId);
				request.setAttribute("linkMain", linkMain);
				request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/view.jsp").forward(request,
						response);

			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 修改
	 */
	protected void updateLinkMan(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String nickname = request.getParameter("nickname");
			String position = request.getParameter("position");
			String phonenum = request.getParameter("phonenum");
			String email = request.getParameter("email");
			String qq = request.getParameter("qq");
			String remarks = request.getParameter("remarks");
			String customerId = request.getParameter("customerId");
			Integer linkMainId = Integer.parseInt(request.getParameter("linkMainId"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			LinkMain linkMain = new LinkMain();
			linkMain.setUpdateTime(dateFormat.parse(dateFormat.format(new Date())));
			linkMain.setName(name);
			linkMain.setSex(sex);
			linkMain.setNickname(nickname);
			linkMain.setPosition(position);
			linkMain.setPhonenum(phonenum);
			linkMain.setEmail(email);
			linkMain.setQq(qq);
			linkMain.setRemarks(remarks);
			linkMain.setLinkMainId(linkMainId);
			linkMain.setCustomerId(Integer.parseInt(customerId));
			linkmanI.update(linkMain);

			JSONObject obj = new JSONObject();
			obj.put("flag", 1);
			obj.put("msg", "添加成功");
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 删除
	 */
	protected void deleteLinkMan(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + "/LinkManServlet?method=getAllLinkMan");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	/*
	 * 搜索
	 */
	protected void getOneLinkMan(HttpServletRequest request, HttpServletResponse response) {
		try {
			LinkMain linkMain = new LinkMain();
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			linkMain.setPhonenum(mobile);
			linkMain.setEmail(email);

			List<LinkMain> list = linkmanI.findAll(linkMain);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 查询列表
	 */
	protected void getAllCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			CustomerDto customerDto = new CustomerDto();
			List<CustomerDto> list2 = customerI.findAll(customerDto);
			request.setAttribute("list2", list2);

			request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
