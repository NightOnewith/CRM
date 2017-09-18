package net.yuanma.transaction;

import net.yuanma.exception.ServiceException;

/**
 * 事务管理接口
 *
 */
public interface TransManagerI {
	//开启事务
	void beginTrans() throws ServiceException;
	
	//提交事务
	void commitTrans() throws ServiceException;
	
	//回滚事务
	void rollbackTrans() throws ServiceException;
}
