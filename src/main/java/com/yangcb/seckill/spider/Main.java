package com.yangcb.seckill.spider;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;

import java.util.List;
import java.util.ArrayList;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-06 17:31
 **/
public class Main {

    public static void main(String[] rags) {

        List<HttpRequest> urls = new ArrayList<HttpRequest>();
        urls.add(new HttpGetRequest("http://zj.zjol.com.cn/home.html?pageIndex=1&pageSize=100"));
        urls.add(new HttpGetRequest("http://zj.zjol.com.cn/politics.html?pageIndex=1&pageSize=100"));
        urls.add(new HttpGetRequest("http://zj.zjol.com.cn/original.html?pageIndex=1&pageSize=100"));
        urls.add(new HttpGetRequest("http://zj.zjol.com.cn/life.html?pageIndex=1&pageSize=100"));
        urls.add(new HttpGetRequest("http://zj.zjol.com.cn/vision.html?pageIndex=1&pageSize=100"));
        urls.add(new HttpGetRequest("http://zj.zjol.com.cn/huatuxia.html?pageIndex=1&pageSize=100"));

        GeccoEngine.create()
                //工程的包路径
                .classpath("com.yangcb.seckill.spider")
                //开始抓取的页面地址
                .start(urls)
                //开启几个爬虫线程
                .thread(10)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(10)
                //使用pc端userAgent
                .mobile(false)
                //开始运行
                .run();


    }


}
