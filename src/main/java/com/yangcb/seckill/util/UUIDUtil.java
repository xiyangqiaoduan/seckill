package com.yangcb.seckill.util;

import java.util.UUID;
/**
 * UUID工具类
 * @author yangcb
 *
 */
public class UUIDUtil {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}	
}
