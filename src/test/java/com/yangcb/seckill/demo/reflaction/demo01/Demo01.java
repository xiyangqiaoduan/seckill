package com.yangcb.seckill.demo.reflaction.demo01;
/**
 * 测试java.lang.Class
 * @author Administrator
 *
 */
public class Demo01 {

	public static void main(String[] args) throws ClassNotFoundException {
		
		String path="com.yangcb.seckill.demo.reflaction.demo01.User";
		Class<?> clazz=Class.forName(path);
		System.out.println(clazz.hashCode());
		Class<?> clazz2=Class.forName(path);
		System.out.println(clazz2.hashCode());
		
		
	}
	
}
