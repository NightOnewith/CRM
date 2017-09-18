package net.yuanma.service.position_menu_relations;

import java.util.List;

import net.yuanma.model.PositionMenuRelations;

public interface PositionMenuRelationsI {

	//通过positionId查询
	List<PositionMenuRelations> findByPositionId(Integer positionId);
	
	//通过positionId删除
	void deleteByPositionId(Integer positionId);
	
	//添加菜单职位关系
	void addPositionMenuRelations(PositionMenuRelations positionMenuRelations);
	
}
