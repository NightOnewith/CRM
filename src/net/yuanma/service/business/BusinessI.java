package net.yuanma.service.business;

import java.util.List;

import com.github.pagehelper.PageInfo;

import net.yuanma.dto.BusinessDto;
import net.yuanma.dto.CustomerBusinessDto;
import net.yuanma.model.Business;

public interface BusinessI {
	// 添加
	int add(Business business);

	// 查询所有
	List<BusinessDto> findAll(BusinessDto businessDto);
	
	PageInfo<BusinessDto> findAllByPage(BusinessDto businessDto, Integer pageNum);

	// 根据id查询
	BusinessDto selectById(int id);

	// 根据id修改
	void update(Business business);

	// 查询所有客户商机
	PageInfo<CustomerBusinessDto> findAllBC(Integer pageNum);

	// 根据时间查询商机
	int findByDate(String starTime, String endTime);

}
