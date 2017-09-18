package net.yuanma.service.businesssource.impl;

import java.util.List;

import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.BusinessSourceMapper;
import net.yuanma.model.BusinessSource;
import net.yuanma.service.businesssource.BusinessSourceI;

public class BusinessSourceImpl implements BusinessSourceI {

	@Override
	public List<BusinessSource> queryAll() {
		BusinessSourceMapper businessSourceMapper = (BusinessSourceMapper) ObjectFactory
				.getObject("businessSourceMapper");
		List<BusinessSource> list = businessSourceMapper.queryAll();
		return list;
	}

}
