package net.yuanma.mapper;

import java.util.List;

import net.yuanma.dto.BusinessDto;
import net.yuanma.dto.CustomerBusinessDto;
import net.yuanma.model.Business;
import net.yuanma.model.BusinessExample;

import org.apache.ibatis.annotations.Param;

public interface BusinessMapper {
	int countByExample(BusinessExample example);

	int deleteByExample(BusinessExample example);

	int deleteByPrimaryKey(Integer businessId);

	int insert(Business record);

	int insertSelective(Business record);

	List<Business> selectByExample(BusinessExample example);

	Business selectByPrimaryKey(Integer businessId);

	int updateByExampleSelective(@Param("record") Business record, @Param("example") BusinessExample example);

	int updateByExample(@Param("record") Business record, @Param("example") BusinessExample example);

	int updateByPrimaryKeySelective(Business record);

	int updateByPrimaryKey(Business record);

	List<BusinessDto> findAll(BusinessDto businessDto);

	// 根据ID查询
	BusinessDto selectById(int id);

	// 查询所有客户商机
	List<CustomerBusinessDto> findAllBC();

	// 根据时间查询商机
	int findByDate(@Param("weekTime") String starTime, @Param("todayTime") String endTime);

}