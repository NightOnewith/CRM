package net.yuanma.service.customer.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.ConstVal;
import net.yuanma.dto.CustomerDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.CustomerMapper;
import net.yuanma.model.Customer;
import net.yuanma.service.customer.CustomerI;

public class CustomerImpl implements CustomerI {

	@Override
	public int CustomerCheck(Customer customer) {
		CustomerMapper customerMapper = (CustomerMapper) ObjectFactory.getObject("customerMapper");
		int row = customerMapper.insert(customer);
		return row;
	}

	/**
	 * 查询所有顾客
	 */
	@Override
	public List<CustomerDto> findAll(CustomerDto customerDto) {
		CustomerMapper customerMapper = (CustomerMapper) ObjectFactory.getObject("customerMapper");
		List<CustomerDto> list = customerMapper.findAll(customerDto);
		return list;
	}
	
	@Override
	public PageInfo<CustomerDto> findAllByPage(CustomerDto customerDto, Integer pageNum) {
		CustomerMapper customerMapper = (CustomerMapper) ObjectFactory.getObject("customerMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<CustomerDto> list = customerMapper.findAll(customerDto);
		return new PageInfo<CustomerDto>(list);
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public CustomerDto selectById(int customerId) {
		CustomerMapper customerMapper = (CustomerMapper) ObjectFactory.getObject("customerMapper");
		CustomerDto customerDto = customerMapper.selectById(customerId);
		return customerDto;
	}

	/**
	 * 修改
	 */
	@Override
	public void update(Customer customer) {
		CustomerMapper customerMapper = (CustomerMapper) ObjectFactory.getObject("customerMapper");
		customerMapper.updateByPrimaryKeySelective(customer);

	}

	/**
	 * 根据时间查询新增客户
	 */
	@Override
	public int findByDate(String starTime, String endTime) {
		CustomerMapper customerMapper = (CustomerMapper) ObjectFactory.getObject("customerMapper");
		int count = customerMapper.findByDate(starTime, endTime);
		return count;
	}

}
