package com.yangcb.seckill.test.resource;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangcb.seckill.dto.Tree;
import com.yangcb.seckill.service.resource.ResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml" })
public class ResourceServiceTest {
	@Autowired
	ResourceService resourceService;

	@Test
	public void testNav() {

		List<Tree> tree = resourceService.getNav(null);

		System.out.println(tree);
		
	}

}
