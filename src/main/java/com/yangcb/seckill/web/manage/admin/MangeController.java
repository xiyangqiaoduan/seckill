package com.yangcb.seckill.web.manage.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.yangcb.seckill.dto.Tree;
import com.yangcb.seckill.entity.Resource;
import com.yangcb.seckill.service.resource.ResourceService;
import com.yangcb.seckill.service.site.SiteService;

@Controller
@RequestMapping("/manage")
public class MangeController {
	
	@Autowired
	private SiteService siteService;
	@Autowired
	private ResourceService resourceService;
	
	

	@RequestMapping("/index")
	public String index(Model model){
	
		model.addAttribute("site", siteService.getSite());
		
		return "/manage/admin/admin";
	}
	
	@RequestMapping(value="/nav",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public List<Tree> nav(@ModelAttribute Resource resource){
		
		return resourceService.getNav(resource);
	}
	
	
	
	
	@RequestMapping("/north")
	public String north(){
		return "manage/layout/north";
	}
	
	@RequestMapping("/west")
	public String west(){
		return "manage/layout/west";
	}
	@RequestMapping("/center")
	public String center(){
		return "manage/center/index";
	}
	
	
	@RequestMapping("tree")
	@ResponseBody
	public Object tree(){
		String json="[{\"id\":\"xtgl\",\"iconCls\":\"plugin\",\"text\":\"系统管理\",\"children\":[{\"id\":\"zygl\",\"text\":\"资源管理\",\"iconCls\":\"database_gear\",\"url\":\"/druid/login.html\"}]}]";
		Object object=JSONUtils.parse(json);
		return object;
	}
	
	
}
