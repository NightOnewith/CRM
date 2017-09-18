package net.yuanma.mapper;

import java.util.List;

import net.yuanma.dto.AdvanceLogDto;
import net.yuanma.model.AdvanceLog;
import net.yuanma.model.AdvanceLogExample;

import org.apache.ibatis.annotations.Param;

public interface AdvanceLogMapper {
    int countByExample(AdvanceLogExample example);

    int deleteByExample(AdvanceLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(AdvanceLog record);

    int insertSelective(AdvanceLog record);

    List<AdvanceLog> selectByExample(AdvanceLogExample example);

    AdvanceLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") AdvanceLog record, @Param("example") AdvanceLogExample example);

    int updateByExample(@Param("record") AdvanceLog record, @Param("example") AdvanceLogExample example);

    int updateByPrimaryKeySelective(AdvanceLog record);

    int updateByPrimaryKey(AdvanceLog record);
    
    //增加推进
    int add(AdvanceLog advanceLog);
    
    //查询所有
    List<AdvanceLogDto> findAll(AdvanceLogDto advanceLogDto);
    
    //根据id查询
    AdvanceLog select(int id);
}