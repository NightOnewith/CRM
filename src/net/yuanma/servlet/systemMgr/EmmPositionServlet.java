package net.yuanma.servlet.systemMgr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import net.yuanma.dto.MenuDto;
import net.yuanma.exception.ServiceException;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.model.EmmPosition;
import net.yuanma.model.Employee;
import net.yuanma.model.Menu;
import net.yuanma.model.PositionMenuRelations;
import net.yuanma.service.EmmPosition.EmmPositionI;
import net.yuanma.service.Employee.EmployeeI;
import net.yuanma.service.menu.MenuI;
import net.yuanma.service.position_menu_relations.PositionMenuRelationsI;

@WebServlet("/EmmPositionServlet")
public class EmmPositionServlet extends HttpServlet {
	private EmployeeI employeeI = (EmployeeI) ObjectFactory.getObject("EmployeeI");
	private EmmPositionI emmPositionI = (EmmPositionI) ObjectFactory.getObject("EmmPositionI");
	private MenuI menuI = (MenuI) ObjectFactory.getObject("menuI");
	private PositionMenuRelationsI positionMenuRelationsI = (PositionMenuRelationsI) ObjectFactory
			.getObject("positionMenuRelationsI");

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
	 * 查询所有职位
	 * 
	 * @param request
	 * @param response
	 */
	protected void getAllEmmPosition(HttpServletRequest request, HttpServletResponse response) {
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
			
			//查询所有职位
			String positionName = request.getParameter("positionName");
			EmmPosition emmPosition = new EmmPosition();
			if (positionName != null && !positionName.equals("")) {
				emmPosition.setPositionName(positionName);
				request.setAttribute("positionName", positionName);
			}
			PageInfo<EmmPosition> emmPositions = emmPositionI.findAllEmmPositionPage(emmPosition, pageNum);
			request.setAttribute("emmPositions", emmPositions);
			
			//查询所有菜单
			MenuDto menuDto = new MenuDto();
			List<MenuDto> menus = menuI.findAllMenu(menuDto);
			request.setAttribute("menus", menus);
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/index.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到create.jsp页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void goToPositionAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/create.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加职位
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void addPosition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String positionName = request.getParameter("positionname");
			String positionLevel = request.getParameter("positionLevel");
			EmmPosition emmPosition = new EmmPosition();
			emmPosition.setPositionName(positionName);
			emmPosition.setPositionLevel(positionLevel);
			emmPositionI.addEmmPosition(emmPosition);
			response.sendRedirect(request.getContextPath() + "/EmmPositionServlet?method=getAllEmmPosition");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查职位名称是否重复
	 * 
	 * @param request
	 * @param response
	 */
	protected void positionNameCheck(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String positionName = request.getParameter("positionname");
			EmmPosition emmPosition = new EmmPosition();
			List<EmmPosition> list = emmPositionI.findAllEmmPosition(emmPosition);
			// 存放所有职位名称
			List<String> positionNames = new ArrayList<String>();
			for (EmmPosition EmmPosition : list) {
				positionNames.add(EmmPosition.getPositionName());
			}
			if (positionNames.contains(positionName)) {
				json.put("status", "0");
				json.put("msg", "该职位名称已存在");
			} else {
				json.put("status", "1");
			}
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除验证
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void deletePostionCheck(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int positionId = Integer.parseInt(request.getParameter("positionId"));
			try {
				emmPositionI.deletePostionCheck(positionId);
				json.put("status", 1);
				json.put("msg", "确认要删除？");
			} catch (ServiceException e) {
				json.put("status", "0");
				json.put("msg", e.getMessage());
			}
			
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除职位
	 * 
	 * @param request
	 * @param response
	 */
	protected void deletePosition(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int positionId = Integer.parseInt(request.getParameter("positionId"));
			emmPositionI.deleteEmmPosition(positionId);
			response.sendRedirect(request.getContextPath() + "/EmmPositionServlet?method=getAllEmmPosition");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取单个职位信息
	 * 
	 * @param request
	 * @param response
	 */
	protected void getUpdatePostionDetailById(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			int positionId = Integer.parseInt(request.getParameter("positionId"));
			// 获得单个职位
			EmmPosition emmPosition = new EmmPosition();
			emmPosition.setPositionId(positionId);
			List<EmmPosition> emm = emmPositionI.findAllEmmPosition(emmPosition);
			request.setAttribute("emm", emm.get(0));
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/update.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新职位
	 * 
	 * @param request
	 * @param response
	 */
	protected void updatePosition(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			// 取值
			int positionId = Integer.parseInt(request.getParameter("positionId"));
			String positionname = request.getParameter("positionname");
			String positionLevel = request.getParameter("positionLevel");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			EmmPosition emmPosition = new EmmPosition();
			emmPosition.setPositionId(positionId);
			emmPosition.setPositionName(positionname);
			emmPosition.setPositionLevel(positionLevel);
			try {
				emmPosition.setUpdateTime(dateFormat.parse(dateFormat.format(new Date())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			emmPositionI.updateEmmPosition(emmPosition);
			response.sendRedirect(request.getContextPath() + "/EmmPositionServlet?method=getAllEmmPosition");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void getLinkPostionDetailById(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/references.jsp").forward(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关联菜单
	 * 
	 * @param request
	 * @param response
	 */
	protected void positionLinks(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String menuIds = request.getParameter("menuIds"); // 1,2,3,4...
			String[] menuId = menuIds.split(","); // [1,2,3,4,...]
			int positionId = Integer.parseInt(request.getParameter("positionId")); // 职位Id
			List<PositionMenuRelations> list = positionMenuRelationsI.findByPositionId(positionId);
			if (list != null) {
				positionMenuRelationsI.deleteByPositionId(positionId);
			}
			for (int i = 0; i < menuId.length; i++) {
				PositionMenuRelations positionMenuRelations = new PositionMenuRelations();
				positionMenuRelations.setPositionId(positionId);
				positionMenuRelations.setMenuId(Integer.parseInt(menuId[i]));
				positionMenuRelationsI.addPositionMenuRelations(positionMenuRelations);
			}

			response.sendRedirect(request.getContextPath() + "/EmmPositionServlet?method=getAllEmmPosition");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
