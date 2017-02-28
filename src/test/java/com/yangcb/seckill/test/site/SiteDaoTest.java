package com.yangcb.seckill.test.site;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.dao.site.SiteDao;
import com.yangcb.seckill.entity.Site;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SiteDaoTest {
	@Resource
	private SiteDao siteDao;

	@Test
	public void testGetSite() {
		Site site = siteDao.getSite();
		System.out.println(site);
	}

}
