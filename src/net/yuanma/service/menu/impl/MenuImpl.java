package net.yuanma.service.menu.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.ConstVal;
import net.yuanma.dto.MenuDto;
import net.yuanma.exception.ServiceException;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.EmmPositionMapper;
import net.yuanma.mapper.MenuMapper;
import net.yuanma.model.EmmPosition;
import net.yuanma.model.Menu;
import net.yuanma.model.PositionMenuRelations;
import net.yuanma.service.menu.MenuI;

public class MenuImpl implements MenuI {

	/**
	 * 查询所有菜单
	 */
	@Override
	public List<MenuDto> findAllMenu(MenuDto menuDto) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		List<MenuDto> allMenu = menuMapper.findAllMenu(menuDto);
		return allMenu;
	}

	@Override
	public PageInfo<MenuDto> findAllMenuPage(MenuDto menuDto, int pageNum) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<MenuDto> allMenu = menuMapper.findAllMenu(menuDto);
		return new PageInfo<MenuDto>(allMenu);
	}

	/**
	 * 查询所有父级菜单
	 */
	@Override
	public List<Menu> findAllParentMenu() {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		List<Menu> allParentMenu = menuMapper.findAllParentMenu();
		return allParentMenu;
	}

	/**
	 * 添加菜单
	 */
	@Override
	public void addMenu(Menu menu) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		menuMapper.insert(menu);
	}

	/**
	 * 更新菜单
	 */
	@Override
	public void updateMenu(Menu menu) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		menuMapper.updateMenu(menu);
	}

	/**
	 * 删除菜单
	 */
	@Override
	public void deleteMenu(Integer menuId) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		menuMapper.deleteByPrimaryKey(menuId);
	}

	/**
	 * 查询员工对应父级菜单
	 */
	@Override
	public List<MenuDto> getAllMenuInfo(Integer employeeId) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		List<MenuDto> menuInfo = menuMapper.getAllMenuInfo(employeeId);
		return menuInfo;
	}

	/**
	 * 校验菜单删除
	 */
	@Override
	public void deleteMenuCheck(Integer menuId) throws ServiceException {
		MenuI menuI = (MenuI) ObjectFactory.getObject("menuI");

		// 1.检查删除的菜单有无子菜单 只能删除子菜单
		List<Menu> menus = menuI.findByMenuId(menuId);
		if (menus.size() > 0) {
			throw new ServiceException("菜单ID:" + menuId + "存在子菜单");
		}

		// 2.检查是否存在关联菜单
		List<PositionMenuRelations> list = menuI.findById(menuId);
		if (list.size() > 0) {
			throw new ServiceException("菜单ID:" + menuId + "存在关联职位");
		}

	}

	/**
	 * 校验菜单是否存在子菜单
	 */
	@Override
	public List<Menu> findByMenuId(Integer menuId) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		List<Menu> menus = menuMapper.findByMenuId(menuId);
		return menus;
	}

	/**
	 * 囧言是否存在关联职位
	 * 
	 */
	@Override
	public List<PositionMenuRelations> findById(Integer menuId) {
		MenuMapper menuMapper = (MenuMapper) ObjectFactory.getObject("menuMapper");
		List<PositionMenuRelations> list = menuMapper.findById(menuId);
		return list;
	}

}
