package net.yuanma.service.customer;

import java.util.List;

import com.github.pagehelper.PageInfo;

import net.yuanma.dto.CustomerDto;
import net.yuanma.model.Customer;

public interface CustomerI {

	int CustomerCheck(Customer customer);

	// 查询所有
	List<CustomerDto> findAll(CustomerDto customerDto);
	
	PageInfo<CustomerDto> findAllByPage(CustomerDto customerDto, Integer pageNum);

	CustomerDto selectById(int customerId);

	void update(Customer customer);

	// 根据时间查询新增客户
	int findByDate(String starTime, String endTime);

}
