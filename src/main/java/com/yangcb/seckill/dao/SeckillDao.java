package com.yangcb.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yangcb.seckill.entity.Seckill;

public interface SeckillDao {
	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * 根据ID 查询单个秒杀对象
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);

	
	/**
	 * 执行秒杀 调用储存过程
	 * @param seckillId
	 * @param userPhone
	 * @param killTime
	 * @return
	 */
	Map<String, Object> killByProcedure(Map<String, Object> param);
	
	
}
