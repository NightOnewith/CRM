package net.yuanma.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yuanma.exception.ServiceException;
import net.yuanma.factory.ObjectFactory;
import net.yuanma.transaction.TransManagerI;
import net.yuanma.transaction.impl.TransManagerImpl;

public class TransactionProxy {
	private static TransManagerI transManager = new TransManagerImpl();

	// 被代理类

	public static Object createProxy(final Object target) {

		// 通过JDK创建的动态代理
		Object proxyObject = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {
						Object res = null;
						try {
							transManager.beginTrans();
							res = method.invoke(target, args);
							transManager.commitTrans();
						} catch (InvocationTargetException e) {
							transManager.rollbackTrans();
							//throw new ServiceException(e);
							throw e.getTargetException();
						}

						return res;
					}
				});
		return proxyObject;
	}

}
