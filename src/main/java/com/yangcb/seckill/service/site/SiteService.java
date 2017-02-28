package com.yangcb.seckill.service.site;

import com.yangcb.seckill.dto.DataResult;
import com.yangcb.seckill.entity.Site;

public interface SiteService {

	Site getSite();
	/**
	 * 修改站点信息
	 * @param site
	 * @return
	 */
	DataResult<Site> updateSite(Site site);
	
	
}
