package net.yuanma.service.Employee.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.ConstVal;
import net.yuanma.dto.EmployeeDto;
import net.yuanma.dto.EmployeeDtoZj;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.EmployeeMapper;
import net.yuanma.model.Employee;
import net.yuanma.service.Employee.EmployeeI;

public class EmployeeImpl implements EmployeeI {

	/**
	 * 查询所有员工
	 */
	@Override
	public List<EmployeeDtoZj> findAllEmployee(EmployeeDtoZj employeeDto) {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		List<EmployeeDtoZj> employeeDtos = employeeMapper.findAllEmployee(employeeDto);
		return employeeDtos;
	}

	@Override
	public PageInfo<EmployeeDtoZj> findAllEmployeePage(EmployeeDtoZj employeeDto, int pageNum) {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<EmployeeDtoZj> employees = employeeMapper.findAllEmployee(employeeDto);
		return new PageInfo<EmployeeDtoZj>(employees);
	}

	/**
	 * 添加员工
	 */
	@Override
	public int addEmployee(Employee employee) {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		int employeeId = employeeMapper.insert(employee);
		return employeeId;
	}

	/**
	 * 修改员工
	 */
	@Override
	public void updateEmployee(Employee employee) {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	@Override
	public List<EmployeeDto> queryAll() {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		List<EmployeeDto> list = employeeMapper.queryAll();
		return list;
	}

	/**
	 * 查询单个员工
	 */
	@Override
	public Employee findEmployeeById(Integer id) {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	/**
	 * 通过positionId查询员工
	 */
	@Override
	public List<Employee> findEmployeeByPositionId(Integer id) {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		List<Employee> list = employeeMapper.findEmployeeByPositionId(id);
		return list;
	}

	/**
	 * 通过名字查询
	 */
	@Override
	public Employee findEmployeeByName(String employeeName) {
		EmployeeMapper employeeMapper = (EmployeeMapper) ObjectFactory.getObject("employeeMapper");
		Employee employee = employeeMapper.findEmployeeByName(employeeName);
		return employee;
	}
}
