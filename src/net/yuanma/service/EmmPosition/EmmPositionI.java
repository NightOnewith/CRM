package net.yuanma.service.EmmPosition;

import java.util.List;

import com.github.pagehelper.PageInfo;

import net.yuanma.exception.ServiceException;
import net.yuanma.model.EmmPosition;

public interface EmmPositionI {
	
	//查询所有职位
	List<EmmPosition> findAllEmmPosition(EmmPosition emmPosition);
	
	PageInfo<EmmPosition> findAllEmmPositionPage(EmmPosition emmPosition, int pageNum);
	
	//添加职位
	void addEmmPosition(EmmPosition emmPosition);
	
	//更新职位
	void updateEmmPosition(EmmPosition emmPosition);
	
	//删除校验
	void deletePostionCheck(Integer positionId) throws ServiceException;
	
	//删除职位
	void deleteEmmPosition(Integer positionId);
	
	//查询单个职位
	EmmPosition findEmmPositionById(Integer positionId);
}
