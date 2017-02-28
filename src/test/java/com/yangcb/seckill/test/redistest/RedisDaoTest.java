package com.yangcb.seckill.test.redistest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.dao.SeckillDao;
import com.yangcb.seckill.dao.cache.RedisDao;
import com.yangcb.seckill.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class RedisDaoTest {

	private long seckillId = 1001;

	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SeckillDao seckillDao;

	@Test
	public void testSeckill() {

		Seckill seckill = redisDao.getSeckill(seckillId);
		
		if (seckill == null) {
			seckill = seckillDao.queryById(seckillId);
			String result = redisDao.putSeckill(seckill);
			System.out.println(result);
			Seckill seckill2 = redisDao.getSeckill(seckillId);
			System.out.println(seckill2);
		}
		System.out.println(seckill);

	}

}
