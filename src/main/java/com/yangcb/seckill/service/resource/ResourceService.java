package com.yangcb.seckill.service.resource;

import java.util.List;

import com.yangcb.seckill.dto.Tree;
import com.yangcb.seckill.dto.TreeGrid;
import com.yangcb.seckill.entity.Resource;

/**
 * 菜单信息信息管理
 * @author yangcb
 *
 */
public interface ResourceService {

	List<Tree> getNav(Resource resource);

	List<TreeGrid> getTreeGrid(Resource resource);
	
}
