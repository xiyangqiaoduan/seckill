package com.yangcb.seckill.demo.reflaction.demo02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo02 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		String path = "com.yangcb.seckill.demo.reflaction.demo01.User";
		Class<?> clazz = Class.forName(path);
		String name = clazz.getName();// 全路径信息
		System.out.println(name);
		name = clazz.getSimpleName();// 类名称
		System.out.println(name);

		// Field[] fields=clazz.getFields();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		
		Field field=clazz.getDeclaredField("name");
		System.out.println(field.getName());
		//获取相关的方法
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods){
			System.out.println(method.getName());
		}
		Method method=clazz.getDeclaredMethod("setName", String.class);
		System.out.println(method.getName());
		//获取构造器
		Constructor<?>[] constructors=clazz.getDeclaredConstructors();
		for(Constructor constructor:constructors){
			System.out.println(constructor);
		}
		
		Constructor constructor=clazz.getDeclaredConstructor(null);
		System.out.println(constructor);
		Constructor constructor2=clazz.getDeclaredConstructor(int.class,String.class,int.class);
		System.out.println(constructor2);
		
		
	}

}
