package com.yangcb.seckill.web.manage.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangcb.seckill.dto.DataResult;
import com.yangcb.seckill.entity.Site;
import com.yangcb.seckill.service.site.SiteService;

@Controller
@RequestMapping("/manage/site")
public class SiteController {

	@Autowired
	private SiteService siteService;
	@RequestMapping(value="/updateSite",produces={"application/json;chareset=utf-8"})
	@ResponseBody
	public DataResult<Site> updateSite(Site site){
		
		DataResult<Site> dataResult=new DataResult<Site>();
		if(site==null){			
			dataResult.setSuccess(false);
			dataResult.setMsg("获取的站点信息错误");
			dataResult.setCode(9999);
		}else if(StringUtils.isBlank(site.getSiteName())){
			dataResult.setSuccess(false);
			dataResult.setMsg("站点名称不能为空");
			dataResult.setCode(9999);
		}
		dataResult=siteService.updateSite(site);
		
		
		return dataResult;
		
	}
	
	
	
}
