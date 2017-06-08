package com.yangcb.seckill.spider;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-06 17:36
 **/
@PipelineName("newsDetailPipeline")
public class NewsDetailPipeline implements Pipeline<NewsDetail> {
    private Logger log= LoggerFactory.getLogger(this.getClass());
    @Override
    public void process(NewsDetail newsDetail) {
        try{
            System.out.println("获取详情信息："+JSON.toJSONString(newsDetail));
        }catch (Exception e){
            log.error("error detail",e);
        }
    }
}
