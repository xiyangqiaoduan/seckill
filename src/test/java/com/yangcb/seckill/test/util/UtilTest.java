package com.yangcb.seckill.test.util;

import java.util.UUID;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testUUID(){
		
		System.out.println(UUID.randomUUID().toString());
		
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
		
		
	}
	
	
	
}
