package net.yuanma.service.menu;

import java.util.List;

import com.github.pagehelper.PageInfo;

import net.yuanma.dto.MenuDto;
import net.yuanma.exception.ServiceException;
import net.yuanma.model.Menu;
import net.yuanma.model.PositionMenuRelations;

public interface MenuI {
	
	//查询所有菜单
	List<MenuDto> findAllMenu(MenuDto menuDto);
	
	PageInfo<MenuDto> findAllMenuPage(MenuDto menuDto, int pageNum);
	
	//查询所有父级菜单
	List<Menu> findAllParentMenu();
	
	//添加菜单
	void addMenu(Menu menu);
	
	//更新菜单
	void updateMenu(Menu menu);
	
	//删除菜单
	void deleteMenu(Integer menuId);
	
	//查询员工对应的父级菜单
	List<MenuDto> getAllMenuInfo(Integer employeeId);
	
	//校验菜单删除
	void deleteMenuCheck(Integer menuId) throws ServiceException;
	
	//校验菜单是否存在子菜单
	List<Menu> findByMenuId(Integer menuId);
	
	//校验菜单是否存在关联职位
	List<PositionMenuRelations> findById(Integer menuId);
}
