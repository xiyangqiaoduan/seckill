package com.yangcb.seckill.test.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.dao.resource.ResourceDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ResouceDaoTest {
	@Autowired
	ResourceDao resourceDao;
	@Test
	public void testNav(){
		System.out.println(resourceDao.nav(null).size());
	}
	
}
