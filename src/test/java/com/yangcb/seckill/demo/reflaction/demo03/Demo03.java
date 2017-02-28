package com.yangcb.seckill.demo.reflaction.demo03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.yangcb.seckill.demo.reflaction.demo01.User;


/***
 * 动态操作构造器方法的属性
 * @author Administrator
 *
 */
public class Demo03 {

	//动态操作构造器
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		String path = "com.yangcb.seckill.demo.reflaction.demo01.User";
		Class<?> clazz = Class.forName(path);
		//初始化对象   调用了User的无参构造方法
		User user=(User) clazz.newInstance();
		
		Constructor constructor=clazz.getDeclaredConstructor(int.class,String.class,int.class);
		
		User user2=(User) constructor.newInstance(1001,"杨春报",27);
		
		System.out.println(user2.getName());
		
		//通过反射API调用普通方法
		User user3=(User) clazz.newInstance();
		Method method=clazz.getDeclaredMethod("setName",String.class);
		method.invoke(user3, "小强强");
		System.out.println(user3.getName());
		//通过反射API操作属性
		User user4=(User) clazz.newInstance();

		Field f=clazz.getDeclaredField("name");
		f.setAccessible(true);//设置不需要安全检查，可以直接访问
		f.set(user4, "夕阳桥段");
		System.out.println(user4.getName());
		System.out.println(f.get(user4));
	}
	
	
	
}
