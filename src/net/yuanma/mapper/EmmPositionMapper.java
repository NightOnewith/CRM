package net.yuanma.mapper;

import java.util.List;
import net.yuanma.model.EmmPosition;
import net.yuanma.model.EmmPositionExample;
import org.apache.ibatis.annotations.Param;

public interface EmmPositionMapper {
    int countByExample(EmmPositionExample example);

    int deleteByExample(EmmPositionExample example);

    int deleteByPrimaryKey(Integer positionId);

    //添加职位
    int insert(EmmPosition record);

    int insertSelective(EmmPosition record);

    List<EmmPosition> selectByExample(EmmPositionExample example);

    //查询单个职位
    EmmPosition selectByPrimaryKey(Integer positionId);

    int updateByExampleSelective(@Param("record") EmmPosition record, @Param("example") EmmPositionExample example);

    int updateByExample(@Param("record") EmmPosition record, @Param("example") EmmPositionExample example);

    int updateByPrimaryKeySelective(EmmPosition record);

    int updateByPrimaryKey(EmmPosition record);
    
    //查询所有职位
    List<EmmPosition> findAllEmmPosition(EmmPosition emmPosition);
    
}