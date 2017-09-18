package net.yuanma.service.task;

import com.github.pagehelper.PageInfo;

import net.yuanma.model.Task;

public interface TaskI {
	
	//查询我的任务
	PageInfo<Task> getMyTask(Integer employeeId, Integer pageNum);
}
