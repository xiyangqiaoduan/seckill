package com.yangcb.seckill.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.dao.SuccessSeckilledDao;
import com.yangcb.seckill.entity.SuccessSeckilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SuccessSeckilledDaoTest {
	@Resource
	private SuccessSeckilledDao successSeckilledDao;
	@Test
	public void insertSuccessKilled(){
		int insertCount=successSeckilledDao.insertSuccessKilled(1001L, 15165732356L);
		System.out.println(insertCount);
	}
	
	@Test
	public void queryByIdWithSeckill(){
		SuccessSeckilled successSeckilled=successSeckilledDao.queryByIdWithSeckill(1001L, 15165732356L);
		System.out.println(successSeckilled);
	}
	
	
}
