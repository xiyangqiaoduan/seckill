package com.yangcb.seckill.service.site.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yangcb.seckill.dao.site.SiteDao;
import com.yangcb.seckill.dto.DataResult;
import com.yangcb.seckill.entity.Site;
import com.yangcb.seckill.service.site.SiteService;

@Service
public class SiteServiceImpl implements SiteService{

	@Resource
	private SiteDao siteDao;
	
	@Override
	public Site getSite() {
		return siteDao.getSite();
	}

	@Override
	public DataResult<Site> updateSite(Site site) {
		DataResult<Site> dataResult=new DataResult<Site>();
		int rowNum=siteDao.updateSite(site);
		if(rowNum==1){
			dataResult.setCode(0000);
			dataResult.setMsg("修改站点信息成功");
			dataResult.setSuccess(true);
		}else{
			dataResult.setCode(0000);
			dataResult.setMsg("修改站点信息失败");
			dataResult.setSuccess(false);
		}
		
		
		return dataResult;
	}

}
