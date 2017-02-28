package com.yangcb.seckill.test.site.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.entity.Site;
import com.yangcb.seckill.service.site.SiteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SiteServiceTest {

	@Autowired
	private SiteService siteService;
	
	@Test
	public void testGetSite(){
		Site site=siteService.getSite();
		System.out.println(site);
	}
	
	
}
