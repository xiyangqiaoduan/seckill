package com.yangcb.seckill.web.manage.druid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class DruidController {

	@RequestMapping("/druid")
	public String index(){
		return "redirect:/druid/login.html";
	}
	
}
