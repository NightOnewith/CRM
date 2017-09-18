package net.yuanma.service.business.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.ConstVal;
import net.yuanma.dto.BusinessDto;
import net.yuanma.dto.CustomerBusinessDto;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.BusinessMapper;
import net.yuanma.model.Business;
import net.yuanma.service.business.BusinessI;

public class BusinessImpl implements BusinessI {
	// 添加
	@Override
	public int add(Business business) {
		BusinessMapper businessMapper = (BusinessMapper) ObjectFactory.getObject("businessMapper");
		int row = businessMapper.insert(business);
		return row;
	}

	/**
	 * 查询所有
	 */
	@Override
	public List<BusinessDto> findAll(BusinessDto businessDto) {
		BusinessMapper businessMapper = (BusinessMapper) ObjectFactory.getObject("businessMapper");
		List<BusinessDto> list = businessMapper.findAll(businessDto);
		return list;
	}
	
	@Override
	public PageInfo<BusinessDto> findAllByPage(BusinessDto businessDto, Integer pageNum) {
		BusinessMapper businessMapper = (BusinessMapper) ObjectFactory.getObject("businessMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<BusinessDto> list = businessMapper.findAll(businessDto);
		return new PageInfo<BusinessDto>(list);
	}

	/**
	 * 根据id查询
	 */

	@Override
	public BusinessDto selectById(int id) {
		BusinessMapper businessMapper = (BusinessMapper) ObjectFactory.getObject("businessMapper");
		BusinessDto businessDto = businessMapper.selectById(id);
		return businessDto;

	}

	/**
	 * 修改
	 */

	@Override
	public void update(Business business) {
		BusinessMapper businessMapper = (BusinessMapper) ObjectFactory.getObject("businessMapper");
		businessMapper.updateByPrimaryKeySelective(business);

	}

	/**
	 * 查询所有客户商机
	 */
	@Override
	public PageInfo<CustomerBusinessDto> findAllBC(Integer pageNum) {
		BusinessMapper businessMapper = (BusinessMapper) ObjectFactory.getObject("businessMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<CustomerBusinessDto> cbDtos = businessMapper.findAllBC();
		return new PageInfo<CustomerBusinessDto>(cbDtos);
	}

	/**
	 * 根据时间查询商机
	 */
	@Override
	public int findByDate(String starTime, String endTime) {
		BusinessMapper businessMapper = (BusinessMapper) ObjectFactory.getObject("businessMapper");
		int count = businessMapper.findByDate(starTime, endTime);
		return count;
	}

}
