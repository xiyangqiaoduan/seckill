package com.yangcb.seckill.service.resource.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangcb.seckill.dao.resource.ResourceDao;
import com.yangcb.seckill.dto.Tree;
import com.yangcb.seckill.dto.TreeGrid;
import com.yangcb.seckill.entity.Resource;
import com.yangcb.seckill.enums.ResourceStatEnum;
import com.yangcb.seckill.service.resource.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public List<Tree> getNav(Resource resource) {
		
		List<Resource> resources=resourceDao.nav(resource);
		List<Tree> trees=new ArrayList<Tree>();
		if(resources!=null&&resources.size()>0){
			
			for(Resource resource2:resources){
				Tree tree=new Tree();
				tree.setId(resource2.getId());
				if(resource2.getPid().equals("0")){
					tree.setState("closed");
				}else{
					tree.setState("open");

				}
				tree.setText(resource2.getName());
				tree.setUrl(resource2.getUrl()==null?"":resource2.getUrl());
				tree.setIconCls(resource2.getIcon());
				trees.add(tree);
			}
		}
		
		
		return trees;
	}

	@Override
	public List<TreeGrid> getTreeGrid(Resource resource) {
	
		Resource r=resourceDao.getResourceById(resource.getId());
		
		List<Resource> resources=resourceDao.nav(resource);
		List<TreeGrid> treeGrids=new ArrayList<TreeGrid>();
		if(resources!=null&&resources.size()>0){
			
			for(Resource resource2:resources){
				
				TreeGrid treeGrid=new TreeGrid();
				BeanUtils.copyProperties(resource2, treeGrid);
				
				treeGrid.setIconCls(resource2.getIcon());
				treeGrid.setTypeId(ResourceStatEnum.getResourceStatEnum(resource2.getType())+"");
				treeGrid.setTypeName(ResourceStatEnum.getResourceStatEnum(resource2.getType()).getStateInfo());
				if(r!=null){
					treeGrid.setPname(r.getName());
					treeGrid.setPid(r.getId());
				}
				
				
				treeGrids.add(treeGrid);
			}
		}
		return treeGrids;
	}

	
	public Resource getResourceById(String id){
		
		return resourceDao.getResourceById(id);
		
	}  
	
	
}
