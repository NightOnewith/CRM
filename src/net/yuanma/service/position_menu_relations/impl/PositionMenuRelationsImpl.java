package net.yuanma.service.position_menu_relations.impl;

import java.util.List;

import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.PositionMenuRelationsMapper;
import net.yuanma.model.PositionMenuRelations;
import net.yuanma.service.position_menu_relations.PositionMenuRelationsI;

public class PositionMenuRelationsImpl implements PositionMenuRelationsI {

	/**
	 * 通过positionId查询
	 */
	@Override
	public List<PositionMenuRelations> findByPositionId(Integer positionId) {
		PositionMenuRelationsMapper pmrm = (PositionMenuRelationsMapper) ObjectFactory
				.getObject("positionMenuRelationsMapper");
		List<PositionMenuRelations> pmrs = pmrm.findByPositionId(positionId);
		return pmrs;
	}

	/**
	 * 通过positionId删除
	 */
	@Override
	public void deleteByPositionId(Integer positionId) {
		PositionMenuRelationsMapper pmrm = (PositionMenuRelationsMapper) ObjectFactory
				.getObject("positionMenuRelationsMapper");
		pmrm.deleteByPositionId(positionId);
	}

	/**
	 * 添加菜单职位关系
	 */
	@Override
	public void addPositionMenuRelations(PositionMenuRelations positionMenuRelations) {
		PositionMenuRelationsMapper pmrm = (PositionMenuRelationsMapper) ObjectFactory
				.getObject("positionMenuRelationsMapper");
		pmrm.insert(positionMenuRelations);
	}

}
