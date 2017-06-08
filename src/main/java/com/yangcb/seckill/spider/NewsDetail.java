package com.yangcb.seckill.spider;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-06 17:34
 **/
@Gecco(matchUrl = "http://zj.zjol.com.cn/news/{code}.html" ,pipelines = "zjNewsDetailPipeline")
public class NewsDetail implements HtmlBean {

    @Text
    @HtmlField(cssPath = "#headline")
    private String title;
    @Text
    @HtmlField(cssPath = "#content > div > div.news_con > div.news-content > div:nth-child(1) > div > p.go-left.post-time.c-gray")
    private String createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
