package com.yangcb.seckill.spider;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import java.util.List;


/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-06 18:07
 **/
@Gecco(matchUrl = "http://zj.zjol.com.cn/{type}.html?pageIndex={pageIndex}&pageSize={pageSize}",pipelines = "zJNewsListPipelines")
public class NewsGeccoList implements HtmlBean {

    @Request
    private HttpRequest request;

    @RequestParameter
    private int pageIndex;
    @RequestParameter
    private int pageSize;



    @RequestParameter
    private  String type;

    @HtmlField(cssPath = "#content > div > div > div.con_index > div.r.main_mod > div > ul > li  > dl > dt > a")
    private List<HrefBean> newList;


    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<HrefBean> getNewList() {
        return newList;
    }

    public void setNewList(List<HrefBean> newList) {
        this.newList = newList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
