package net.yuanma.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import net.yuanma.exception.DaoException;

public class MyBatisUtils {
	private static SqlSessionFactory sf;
	private static ThreadLocal<SqlSession> threadLocal;
	
	/** 
     *  
     * 加载配置文件 
     */  
	static {
		try {
			threadLocal = new ThreadLocal<SqlSession>();
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			sf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("MyBaitsUtil初始化错误" + e);
		}
	}

	/** 
     *  
     * 获取SqlSession 
     *  
     * @return 
     */  
	public static SqlSession getSession() {
		SqlSession session = threadLocal.get();
		try {
			if (session == null) {
				session = sf.openSession(false);
				threadLocal.set(session);
			}
		} catch (Exception e) {
			throw new DaoException("数据访问失败", e);
		}
		return session;
	}

	/** 
     * 关闭Session 
     */  
	public static void closeSession() {
		SqlSession session = threadLocal.get();
		if (session != null) {
			session.clearCache();
			session.close();
			threadLocal.remove();
		}
	}
}
