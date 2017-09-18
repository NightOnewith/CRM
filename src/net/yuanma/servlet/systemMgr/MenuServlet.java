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
import net.yuanma.model.Menu;
import net.yuanma.service.menu.MenuI;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
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
	 * 查询所有菜单
	 * 
	 * @param request
	 * @param response
	 */
	protected void getAllMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset-utf-8");

			// 分页中的东西
			int pageNum;
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr != null) {
				pageNum = Integer.parseInt(pageNoStr);
			} else {
				pageNum = 1;
			}

			String name = request.getParameter("name");
			MenuDto menuDto = new MenuDto();
			if (name != null && !name.equals("")) {
				menuDto.setMenuName(name);
				request.setAttribute("name", name);
			}

			PageInfo<MenuDto> menus = menuI.findAllMenuPage(menuDto, pageNum);
			request.setAttribute("menus", menus);
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/menu/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有父级菜单
	 * 
	 * @param request
	 * @param response
	 */
	protected void getAllParentMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			List<Menu> parentMenus = menuI.findAllParentMenu();
			request.setAttribute("parentMenus", parentMenus);
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/menu/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 添加菜单
	 * 
	 * @param request
	 * @param response
	 */
	protected void addMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			int parentId = Integer.parseInt(request.getParameter("menu-parent_id"));
			String menuName = request.getParameter("menu-name");
			String menuURL = request.getParameter("menu-url");
			String menuIcon = request.getParameter("menu-icon");
			Menu menu = new Menu();
			menu.setMenuName(menuName);
			menu.setMenuUrl(menuURL);
			menu.setPictures(menuIcon);
			if (parentId == 0) {
				menu.setParentMenuId(null);
			} else {
				menu.setParentMenuId(parentId);
			}
			menuI.addMenu(menu);
			response.sendRedirect(request.getContextPath() + "/MenuServlet?method=getAllMenu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除菜单
	 * 
	 * @param request
	 * @param response
	 */
	protected void deleteMenuById(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			int menuId = Integer.parseInt(request.getParameter("menuId"));
			menuI.deleteMenu(menuId);
			response.sendRedirect(request.getContextPath() + "/MenuServlet?method=getAllMenu");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询单个菜单传入update.jsp页面
	 * 
	 * @param request
	 * @param response
	 */
	protected void getUpdateMenudetailById(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int menuId = Integer.parseInt(request.getParameter("menuId"));

			// 获取所有父级菜单
			List<Menu> parentMenus = menuI.findAllParentMenu();
			request.setAttribute("parentMenus", parentMenus);

			// 获取单个菜单
			MenuDto menuDto = new MenuDto();
			menuDto.setMenuId(menuId);
			List<MenuDto> menus = menuI.findAllMenu(menuDto);
			request.setAttribute("menu", menus.get(0));

			request.getRequestDispatcher("/WEB-INF/view/systemSettings/menu/update.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新菜单
	 * 
	 * @param request
	 * @param response
	 */
	protected void updateMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			int menuId = Integer.parseInt(request.getParameter("menuId"));
			String menuName = request.getParameter("menu-name");
			String menuURL = request.getParameter("menu-url");
			String menuIcon = request.getParameter("menu-icon");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Menu menu = new Menu();
			menu.setMenuId(menuId);
			int parentId = Integer.parseInt(request.getParameter("menu-parent_id"));
			if (parentId != 0) {
				menu.setParentMenuId(parentId);
			} 
			menu.setMenuName(menuName);
			menu.setMenuUrl(menuURL);
			menu.setPictures(menuIcon);
			try {
				menu.setUpdateTime(dateFormat.parse(dateFormat.format(new Date())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			menuI.updateMenu(menu);
			response.sendRedirect(request.getContextPath() + "/MenuServlet?method=getAllMenu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1.检查删除的菜单有无子菜单 只能删除子菜单
	 * 2.是否存在职位关联
	 * 
	 * @param request
	 * @param response
	 */
	protected void checkDeleteMenuId(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int menuId = Integer.parseInt(request.getParameter("menuId"));
			
			try {
				menuI.deleteMenuCheck(menuId);
				json.put("status", 1);
			} catch (ServiceException e) {
				json.put("status", "0");
				json.put("msg", e.getMessage());
			}
			
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
