package com.yangcb.seckill.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.dao.SeckillDao;
import com.yangcb.seckill.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SeckillDaoTest {

	// 注入依赖
	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testQueryById() {
		long seckillId=1000;
		Seckill seckill=seckillDao.queryById(seckillId);
		System.out.println(seckill.getName());
		System.err.println(seckill);	
	}


	@Test
	public void testQueryAll() {

		List<Seckill>  seckills=seckillDao.queryAll(1, 5);
		for(Seckill seckill:seckills){
			System.out.println(seckill);
		}
	}

	
	
	@Test
	public void reduceNumber() {
		Date date=new Date();
		int updateCount=seckillDao.reduceNumber(1000,date );
		System.out.println(updateCount);
	}

}
