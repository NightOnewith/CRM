package net.yuanma.mapper;

import java.util.List;

import net.yuanma.model.Task;

public interface TaskMapper {

	//查询我的任务
	List<Task> getMyTask(Integer employeeId);
}
