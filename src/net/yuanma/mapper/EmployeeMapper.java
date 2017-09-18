package net.yuanma.mapper;

import java.util.List;

import net.yuanma.dto.EmployeeDto;
import net.yuanma.dto.EmployeeDtoZj;
import net.yuanma.model.Employee;
import net.yuanma.model.EmployeeExample;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
	int countByExample(EmployeeExample example);

	int deleteByExample(EmployeeExample example);

	int deleteByPrimaryKey(Integer employeeId);

	// 添加员工
	int insert(Employee record);

	int insertSelective(Employee record);

	List<Employee> selectByExample(EmployeeExample example);

	// 查询单个员工
	Employee selectByPrimaryKey(Integer employeeId);

	int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

	int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

	// 修改员工
	int updateByPrimaryKeySelective(Employee record);

	int updateByPrimaryKey(Employee record);

	// 查询所有员工
	List<EmployeeDtoZj> findAllEmployee(EmployeeDtoZj employeeDto);

	List<EmployeeDto> queryAll();

	// 通过positionId查询员工
	List<Employee> findEmployeeByPositionId(Integer id);

	// 通过名字查询
	Employee findEmployeeByName(@Param("employeeName") String employeeName);

}