package net.yuanma.service.EmmPosition.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.ConstVal;
import net.yuanma.dto.EmployeeDtoZj;
import net.yuanma.exception.ServiceException;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.EmmPositionMapper;
import net.yuanma.model.EmmPosition;
import net.yuanma.model.Employee;
import net.yuanma.model.PositionMenuRelations;
import net.yuanma.service.EmmPosition.EmmPositionI;
import net.yuanma.service.Employee.EmployeeI;
import net.yuanma.service.menu.MenuI;
import net.yuanma.service.position_menu_relations.PositionMenuRelationsI;

public class EmmPositionImpl implements EmmPositionI {

	/**
	 * 查询所有职位
	 */
	@Override
	public List<EmmPosition> findAllEmmPosition(EmmPosition emmPosition) {
		EmmPositionMapper emmPositionMapper = (EmmPositionMapper) ObjectFactory.getObject("emmPositionMapper");
		List<EmmPosition> emmPositions = emmPositionMapper.findAllEmmPosition(emmPosition);
		return emmPositions;
	}
	
	@Override
	public PageInfo<EmmPosition> findAllEmmPositionPage(EmmPosition emmPosition, int pageNum) {
		EmmPositionMapper emmPositionMapper = (EmmPositionMapper) ObjectFactory.getObject("emmPositionMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<EmmPosition> emmPositions = emmPositionMapper.findAllEmmPosition(emmPosition);
		return new PageInfo<EmmPosition>(emmPositions);
	}

	/**
	 * 添加职位
	 */
	@Override
	public void addEmmPosition(EmmPosition emmPosition) {
		EmmPositionMapper emmPositionMapper = (EmmPositionMapper) ObjectFactory.getObject("emmPositionMapper");
		emmPositionMapper.insert(emmPosition);
	}

	/**
	 * 更新职位
	 */
	@Override
	public void updateEmmPosition(EmmPosition emmPosition) {
		EmmPositionMapper emmPositionMapper = (EmmPositionMapper) ObjectFactory.getObject("emmPositionMapper");
		emmPositionMapper.updateByPrimaryKeySelective(emmPosition);
	}

	/**
	 * 删除职位
	 */
	@Override
	public void deleteEmmPosition(Integer positionId) {
		EmmPositionMapper emmPositionMapper = (EmmPositionMapper) ObjectFactory.getObject("emmPositionMapper");
		emmPositionMapper.deleteByPrimaryKey(positionId);
	}

	/**
	 * 查询单个职位
	 */
	@Override
	public EmmPosition findEmmPositionById(Integer positionId) {
		EmmPositionMapper emmPositionMapper = (EmmPositionMapper) ObjectFactory.getObject("emmPositionMapper");
		EmmPosition emmPosition = emmPositionMapper.selectByPrimaryKey(positionId);
		return emmPosition;
	}

	/**
	 * 校验删除
	 * @throws ServiceException 
	 */
	@Override
	public void deletePostionCheck(Integer positionId) throws ServiceException {
		EmployeeI employeeI = (EmployeeI) ObjectFactory.getObject("EmployeeI");
		EmmPositionI emmPositionI = (EmmPositionI) ObjectFactory.getObject("EmmPositionI");
		PositionMenuRelationsI positionMenuRelationsI = (PositionMenuRelationsI) ObjectFactory
				.getObject("positionMenuRelationsI");
		// 1.验证职位是否存在
		EmmPosition emmPosition = emmPositionI.findEmmPositionById(positionId);
		if (emmPosition == null) {
			throw new ServiceException("职位ID:" + positionId + "不存在对应职位");
		}

		// 2.验证职位是否存在对应员工
		List<Employee> list = employeeI.findEmployeeByPositionId(positionId);
		if (list.size() > 0) {
			throw new ServiceException("职位ID:" + positionId + "存在对应员工");
		}

		// 3.验证职位菜单关系
		List<PositionMenuRelations> pmlist = positionMenuRelationsI.findByPositionId(positionId);
		if (pmlist.size() > 0) {
			throw new ServiceException("职位ID:" + positionId + "存在关联菜单");
		}
	}

}
