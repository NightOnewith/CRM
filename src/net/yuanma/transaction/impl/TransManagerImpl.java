package net.yuanma.transaction.impl;

import org.apache.ibatis.session.SqlSession;

import net.yuanma.exception.DaoException;
import net.yuanma.exception.ServiceException;
import net.yuanma.transaction.TransManagerI;
import net.yuanma.util.MyBatisUtils;

/**
 * 事务管理的实现类
 *
 */
public class TransManagerImpl implements TransManagerI {

	@Override
	public void beginTrans() throws ServiceException {
		SqlSession session = null;
		try {
			session = MyBatisUtils.getSession();
		} catch (Exception e) {
			throw new DaoException("数据访问失败", e);
		}
	}

	@Override
	public void commitTrans() throws ServiceException {
		SqlSession session = null;
		try {
			session = MyBatisUtils.getSession();
			session.commit();
		} catch (Exception e) {
			throw new DaoException("数据访问失败", e);
		} finally {
			MyBatisUtils.closeSession();
		}
	}

	@Override
	public void rollbackTrans() throws ServiceException {
		SqlSession session = null;
		try {
			session = MyBatisUtils.getSession();
			session.rollback();
		} catch (Exception e) {
			throw new DaoException("数据访问失败", e);
		} finally {
			MyBatisUtils.closeSession();
		}
	}

}
