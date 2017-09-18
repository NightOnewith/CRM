package net.yuanma.mapper;

import java.util.List;

import net.yuanma.dto.MenuDto;
import net.yuanma.model.Menu;
import net.yuanma.model.MenuExample;
import net.yuanma.model.PositionMenuRelations;

import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
	int countByExample(MenuExample example);

	int deleteByExample(MenuExample example);

	int deleteByPrimaryKey(Integer menuId);

	//添加菜单
	int insert(Menu record);

	int insertSelective(Menu record);

	List<Menu> selectByExample(MenuExample example);

	Menu selectByPrimaryKey(Integer menuId);

	int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

	int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

	int updateByPrimaryKeySelective(Menu record);

	int updateByPrimaryKey(Menu record);

	// 查询所有菜单
	List<MenuDto> findAllMenu(MenuDto menuDto);
	
	//查询所有父级菜单
	List<Menu> findAllParentMenu();
	
	//更新菜单
	void updateMenu(Menu menu);
	
	//查询员工对应父级菜单
	List<MenuDto> getAllMenuInfo(Integer employeeId);
	
	//校验菜单是否存在子菜单
	List<Menu> findByMenuId(@Param("menuId") Integer menuId);
	
	//校验菜单是否关联职位
	List<PositionMenuRelations> findById(@Param("menuId") Integer menuId);
}