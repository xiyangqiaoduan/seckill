package com.yangcb.seckill.web.manage.admin.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangcb.seckill.dto.TreeGrid;
import com.yangcb.seckill.entity.Resource;
import com.yangcb.seckill.service.resource.ResourceService;
import com.yangcb.seckill.util.UUIDUtil;

@Controller
@RequestMapping("/manage/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	
	@RequestMapping("/index")
	public String index(){
		return "manage/resource/index";		
	}

	@RequestMapping(value="treeGrid",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public List<TreeGrid> treeGrid(Resource resource){
		return resourceService.getTreeGrid(resource);
	}
	
	
	@RequestMapping("/add")
	public String add(String pid,Model model){
		
		String id=UUIDUtil.getUUID();
		model.addAttribute("id", id);
		
		return "manage/resource/add";
	}

}
