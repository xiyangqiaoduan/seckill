package com.yangcb.seckill.test.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yangcb.seckill.util.HttpClientUtil;

public class HttpClientTest {
	@Test
	public void testHttpClient() {
		// System.out.println(HttpClientUtil.get("www.baidu.com"));

		Map<String, String> param = new HashMap<String, String>();
		param.put("wd", "java");

		System.out.println(HttpClientUtil.get("www.baidu.com", param));

	}

	@Test
	public void testHttpClient2() {
		String str = HttpClientUtil.get("http://m.maoyan.com/movie/78421.json");

		// Object object = JSON.parse(str);
		//
		// System.out.println(object);

		JSONObject jsonObject = JSON.parseObject(str);

		System.out.println(jsonObject.get("data"));

	}

	@Test
	public void testHttpClient3() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("num", "100");
		String str = HttpClientUtil.post("http://127.0.0.1:8080/seckill/manage/index.json", param);
		System.out.println(str);
	}

}
