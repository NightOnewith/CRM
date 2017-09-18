package net.yuanma.mapper;

import java.util.List;
import net.yuanma.model.BusinessSource;
import net.yuanma.model.BusinessSourceExample;
import org.apache.ibatis.annotations.Param;

public interface BusinessSourceMapper {
	int countByExample(BusinessSourceExample example);

	int deleteByExample(BusinessSourceExample example);

	int insert(BusinessSource record);

	int insertSelective(BusinessSource record);

	List<BusinessSource> selectByExample(BusinessSourceExample example);

	int updateByExampleSelective(@Param("record") BusinessSource record,
			@Param("example") BusinessSourceExample example);

	int updateByExample(@Param("record") BusinessSource record, @Param("example") BusinessSourceExample example);

	List<BusinessSource> queryAll();
}