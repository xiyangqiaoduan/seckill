package com.yangcb.seckill.dao.resource;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yangcb.seckill.entity.Resource;

public interface ResourceDao {
	
	public List<Resource> nav(Resource resource);

	public Resource getResourceById(@Param("id") String id);

}
