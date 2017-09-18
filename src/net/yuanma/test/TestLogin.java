package net.yuanma.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import net.yuanma.mapper.LogInMapper;
import net.yuanma.model.LogIn;
import net.yuanma.util.MyBatisUtils;

public class TestLogin {

	@Test
	public void testQueryAll() {
		try {
			SqlSession session = MyBatisUtils.getSession();
			LogInMapper mapper = session.getMapper(LogInMapper.class);
			List<LogIn> list = mapper.findAll();
			for (LogIn logIn : list) {
				System.out.println(logIn);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			MyBatisUtils.closeSession();
		}
	}
}
