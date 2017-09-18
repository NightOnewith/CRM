package net.yuanma.service.Department.impl;

import java.util.List;

import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.DepartmentMapper;
import net.yuanma.model.Department;
import net.yuanma.service.Department.DepartmentI;

public class DepartmentImpl implements DepartmentI {

	/**
	 * 查询所有部门
	 */
	@Override
	public List<Department> findAllDepartment() {
		DepartmentMapper departmentMapper = (DepartmentMapper)ObjectFactory.getObject("departmentMapper");
		List<Department> departments = departmentMapper.findAllDepartment();
		return departments;
	}

}
