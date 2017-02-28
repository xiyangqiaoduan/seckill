package com.yangcb.seckill.demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@SuppressWarnings("all")
public class Demo02 {

	@YcbAnnotation(age = 19, id = 1001, studentName = "小强", schools = { "aa", "bb" })
	public void test() {

	}

	public static void main(String[] args) {
		try {

			Class clazz = Class.forName("com.yangcb.seckill.demo.YcbStudent");

			Annotation[] annotations = clazz.getAnnotations();

			for (Annotation annotation : annotations) {
				System.out.println(annotation);
			}
			//获取注解
			YcbTable ycbTable = (YcbTable) clazz.getAnnotation(YcbTable.class);

			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				YcbField ycbField=field.getAnnotation(YcbField.class);
				System.out.println(ycbField.cloumnName()+"-->"+ycbField.type()+"-->"+ycbField.length());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
