package com.yangcb.seckill.web.manage;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yangcb.seckill.entity.Seckill;
import com.yangcb.seckill.util.HttpClientUtil;

@Controller
@RequestMapping("/manage")
public class SeckillManageController {

	@RequestMapping(value = { "/index.do", "/hello.do" }, method = RequestMethod.GET, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public Seckill test01() {
		Seckill seckill = new Seckill();
		seckill.setCreateTime(new Date());
		seckill.setEndTime(new Date());
		seckill.setName("测试信息");
		seckill.setNumber(1000);
		seckill.setSeckillId(10000);
		seckill.setStartTime(new Date());
		return seckill;
	}

	@RequestMapping(value = "/index.json")
	@ResponseBody
	public Object test02(@RequestHeader("host") String hostName, @RequestHeader("Accept") String acceptType,
			@RequestHeader("Accept-Language") String acceptLang, @RequestHeader("Accept-Encoding") String acceptEnc,
			@RequestHeader("Cache-Control") String cacheCon, @RequestHeader("Cookie") String cookie,
			@RequestHeader("User-Agent") String userAgent, @RequestParam(defaultValue = "100") String num) {

		System.out.println("Host : " + hostName);
		System.out.println("Accept : " + acceptType);
		System.out.println("Accept Language : " + acceptLang);
		System.out.println("Accept Encoding : " + acceptEnc);
		System.out.println("Cache-Control : " + cacheCon);
		System.out.println("Cookie : " + cookie);
		System.out.println("User-Agent : " + userAgent);
		System.out.println(num);
		String str = HttpClientUtil.get("http://m.maoyan.com/movie/" + num + ".json");
		Object object = JSON.parse(str);
		return object;
	}

}
