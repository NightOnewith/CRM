package net.yuanma.service.task.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.yuanma.consts.ConstVal;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.mapper.TaskMapper;
import net.yuanma.model.Task;
import net.yuanma.service.task.TaskI;

public class TaskImpl implements TaskI {

	/**
	 * 查询我的任务
	 */
	@Override
	public PageInfo<Task> getMyTask(Integer employeeId, Integer pageNum) {
		TaskMapper taskMapper = (TaskMapper) ObjectFactory.getObject("taskMapper");
		PageHelper.startPage(pageNum, ConstVal.PAGE_SIZE);
		List<Task> myTasks = taskMapper.getMyTask(employeeId);
		return new PageInfo<Task>(myTasks);
	}
}
