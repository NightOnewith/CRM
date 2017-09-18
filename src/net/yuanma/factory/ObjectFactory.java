package net.yuanma.factory;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import net.yuanma.proxy.TransactionProxy;
import net.yuanma.util.MyBatisUtils;

/**
 * <工厂类>
 * @author zj
 * @version [V1.0.0,2017-4-10]
 *
 */
public class ObjectFactory
{

	private static Map<String, Object> objects = new HashMap<String, Object>();
	static
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(ObjectFactory.class.getClassLoader().getResourceAsStream("bean.properties")));
			String s = null;
			while ((s = br.readLine()) != null)
			{
				String[] entry = s.split("=");
				if ("mybatisMapper".equals(entry[0]))
				{
					String daoPackage = entry[1];
					daoPackage = daoPackage.replace(".", "/");
					URL url = ObjectFactory.class.getClassLoader().getResource(daoPackage);
					File dir = new File(url.toURI());
					String[] fileNames = dir.list();
					for (String fileName : fileNames)
					{
						if (fileName.endsWith(".class"))
						{
							String daoClassName = entry[1] + "." + fileName.substring(0, fileName.lastIndexOf(".class"));
							Class daoClass = Class.forName(daoClassName);
							String key = daoClass.getSimpleName();
							String firstStr = key.charAt(0) + "";
							key = firstStr.toLowerCase() + key.substring(1);
							objects.put(key, daoClass);
						}
					}
					continue;
				}
				objects.put(entry[0], Class.forName(entry[1]).newInstance());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError("ObjectFactory初始化错误" + e);
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Object getObject(String key)
	{

		final Object obj = objects.get(key);
		//dao层
		if (obj instanceof Class)
		{
			SqlSession session = MyBatisUtils.getSession();
			final Object mapper = session.getMapper((Class) obj);
			return mapper;
		}else{//service层
			return TransactionProxy.createProxy(obj);
		}
	}
}

