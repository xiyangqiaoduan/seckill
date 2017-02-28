package com.yangcb.seckill.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.dto.Exposer;
import com.yangcb.seckill.dto.SeckillExecution;
import com.yangcb.seckill.entity.Seckill;
import com.yangcb.seckill.exception.RepeatKillException;
import com.yangcb.seckill.exception.SeckillCloseException;
import com.yangcb.seckill.service.SeckillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class SeckillServiceTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list{}", list);
	}

	@Test
	public void testGetById() {
		Seckill seckill = seckillService.getById(1000);
		logger.info("seckill{}", seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		Exposer exposer = seckillService.exportSeckillUrl(1001);
		logger.info("exposer{}", exposer);
	}

	@Test
	public void testExecuteSeckill() {
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(1000L, 15165732356L,
					"699d36e9d75790b2c1d8f0ab5502e779");
			logger.info("seckillExecution{}", seckillExecution);			
		} catch (RepeatKillException repeat) {
			logger.info(repeat.getMessage());
		}catch(SeckillCloseException e){
			logger.info(e.getMessage());
		}
	}
	
	@Test
	public void testExecuteSeckill02(){
		Exposer exposer = seckillService.exportSeckillUrl(1001);
		logger.info("exposer{}", exposer);
		
		try {
//			SeckillExecution seckillExecution = seckillService.executeSeckill(1001L, 15165732300L,
//					exposer.getMd5());
			SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(1001L, 15165732355L,
			exposer.getMd5());
			logger.info("seckillExecution{}", seckillExecution);			
		} catch (RepeatKillException repeat) {
			logger.info(repeat.getMessage());
		}catch(SeckillCloseException e){
			logger.info(e.getMessage());
		}
		
	}

}
