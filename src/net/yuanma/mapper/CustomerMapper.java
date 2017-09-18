package net.yuanma.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.yuanma.dto.CustomerDto;
import net.yuanma.model.Customer;
import net.yuanma.model.CustomerExample;

public interface CustomerMapper {
	int countByExample(CustomerExample example);

	int deleteByExample(CustomerExample example);

	int deleteByPrimaryKey(Integer customerId);

	int insert(Customer record);

	int insertSelective(Customer record);

	List<Customer> selectByExample(CustomerExample example);

	Customer selectByPrimaryKey(Integer customerId);

	int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

	int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);

	// 查询所有
	List<CustomerDto> findAll(CustomerDto customerDto);

	// 根据id查询
	CustomerDto selectById(Integer customerId);

	// 根据时间查询新增客户
	int findByDate(@Param("weekTime") String weekTime, @Param("todayTime") String todayTime);

}