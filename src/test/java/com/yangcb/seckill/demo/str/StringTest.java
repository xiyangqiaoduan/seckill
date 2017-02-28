package com.yangcb.seckill.demo.str;

import org.junit.Test;

import com.yangcb.seckill.entity.Seckill;

public class StringTest {
	@Test
	public void test01() {
		String str = "hello str";
		String str2 = "hello str";
		System.out.println(str.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str==str2);
		
		StringBuffer sb=new StringBuffer(str);
		sb.append("h");
		Seckill seckill=new Seckill();
		seckill.setName("hello str");
		System.out.println(seckill.getName()==str);

	}

}
