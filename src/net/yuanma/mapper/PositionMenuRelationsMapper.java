package net.yuanma.mapper;

import java.util.List;
import net.yuanma.model.PositionMenuRelations;
import net.yuanma.model.PositionMenuRelationsExample;
import org.apache.ibatis.annotations.Param;

public interface PositionMenuRelationsMapper {
	int countByExample(PositionMenuRelationsExample example);

	int deleteByExample(PositionMenuRelationsExample example);

	int deleteByPrimaryKey(Integer relationId);

	// 添加菜单职位关系
	int insert(PositionMenuRelations record);

	int insertSelective(PositionMenuRelations record);

	List<PositionMenuRelations> selectByExample(PositionMenuRelationsExample example);

	PositionMenuRelations selectByPrimaryKey(Integer relationId);

	int updateByExampleSelective(@Param("record") PositionMenuRelations record,
			@Param("example") PositionMenuRelationsExample example);

	int updateByExample(@Param("record") PositionMenuRelations record,
			@Param("example") PositionMenuRelationsExample example);

	int updateByPrimaryKeySelective(PositionMenuRelations record);

	int updateByPrimaryKey(PositionMenuRelations record);

	// 通过positionId查询
	List<PositionMenuRelations> findByPositionId(Integer positionId);

	// 通过positionId删除
	void deleteByPositionId(Integer positionId);

}