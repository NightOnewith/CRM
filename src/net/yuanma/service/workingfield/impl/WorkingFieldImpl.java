package net.yuanma.service.workingfield.impl;

import java.util.List;

import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.WorkingFieldMapper;
import net.yuanma.model.WorkingField;
import net.yuanma.service.workingfield.WorkingFieldI;

public class WorkingFieldImpl implements WorkingFieldI {

	@Override
	public List<WorkingField> queryAll() {
		WorkingFieldMapper workingFieldMapper=(WorkingFieldMapper) ObjectFactory.getObject("workingFieldMapper");
		List<WorkingField> list=workingFieldMapper.queryAll();
		return list;
		
	}

}
