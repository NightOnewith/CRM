package net.yuanma.service.businesstype.impl;

import java.util.List;

import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.BusinessTypeMapper;
import net.yuanma.model.BusinessType;
import net.yuanma.service.businesstype.BusinessTypeI;

public class BusinessTypeImpl implements BusinessTypeI {

	@Override
	public List<BusinessType> queryAll() {
		BusinessTypeMapper businessTypeMapper = (BusinessTypeMapper) ObjectFactory.getObject("businessTypeMapper");
		List<BusinessType> list = businessTypeMapper.queryAll();
		return list;
	}

}
