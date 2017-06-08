package com.yangcb.seckill.spider;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-06 18:09
 **/
public class NewsListPipelines implements Pipeline<NewsGeccoList> {


    public void process(NewsGeccoList zjNewsGeccoList) {
        HttpRequest request=zjNewsGeccoList.getRequest();

        for (HrefBean bean:zjNewsGeccoList.getNewList()){
            //进入祥情页面抓取
            SchedulerContext.into(request.subRequest("http://zj.zjol.com.cn"+bean.getUrl()));
        }
        int page=zjNewsGeccoList.getPageIndex()+1;
        String type= zjNewsGeccoList.getType();
        int maxPage=388;
        if(type.equals("home")){
            maxPage=388;
        }
        if(type.equals("politics")){
            maxPage=64;
        }
        if(type.equals("original")){
            maxPage=61;
        }
        if(type.equals("life")){
            maxPage=46;
        }
        if(type.equals("vision")){
            maxPage=48;
        }
        if (type.equals("huatuxia")){
            maxPage=17;
        }

        if(page<=maxPage){
            System.out.println("type=" + type+ " now page = "+page);
            String nextUrl = "http://zj.zjol.com.cn/home.html?pageIndex="+page+"&pageSize=100";
            //抓取下一页
            SchedulerContext.into(request.subRequest(nextUrl));
        }

    }

}
