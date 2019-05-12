package com.xmh.factory;

import java.io.FileReader;
import java.util.Properties;

public class BasicFactory {

	private static BasicFactory factory=new BasicFactory();
	private static Properties prop=null;
	private BasicFactory() {}
	static {
		try {
			prop=new Properties();
			prop.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public static BasicFactory getFactory() {
		return factory;
	}
	public <T> T getInstance(Class <T> clazz){
		try {
			String infName=clazz.getSimpleName();//接口名字
			String impName=prop.getProperty(infName);//在配置文件中找到实现的类名
			return (T) Class.forName(impName).newInstance();//生成对象
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
