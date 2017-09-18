package net.yuanma.service.Employee;

import java.util.List;

import com.github.pagehelper.PageInfo;

import net.yuanma.dto.EmployeeDto;
import net.yuanma.dto.EmployeeDtoZj;
import net.yuanma.model.Employee;

public interface EmployeeI {

	// 查询所有员工
	List<EmployeeDtoZj> findAllEmployee(EmployeeDtoZj employeeDto);

	PageInfo<EmployeeDtoZj> findAllEmployeePage(EmployeeDtoZj employeeDto, int pageNum);

	// 添加员工
	int addEmployee(Employee employee);

	// 修改员工
	void updateEmployee(Employee employee);

	List<EmployeeDto> queryAll();

	// 查询单个员工
	Employee findEmployeeById(Integer id);

	// 通过positionId查询员工
	List<Employee> findEmployeeByPositionId(Integer id);

	// 通过名字查询
	Employee findEmployeeByName(String employeeName);

}
