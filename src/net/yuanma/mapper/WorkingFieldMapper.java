package net.yuanma.mapper;

import java.util.List;
import net.yuanma.model.WorkingField;
import net.yuanma.model.WorkingFieldExample;
import org.apache.ibatis.annotations.Param;

public interface WorkingFieldMapper {
    int countByExample(WorkingFieldExample example);

    int deleteByExample(WorkingFieldExample example);

    int insert(WorkingField record);

    int insertSelective(WorkingField record);

    List<WorkingField> selectByExample(WorkingFieldExample example);

    int updateByExampleSelective(@Param("record") WorkingField record, @Param("example") WorkingFieldExample example);

    int updateByExample(@Param("record") WorkingField record, @Param("example") WorkingFieldExample example);

    List<WorkingField> queryAll();
}
