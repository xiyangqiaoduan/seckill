package com.yangcb.seckill.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yangcb.seckill.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-07 10:51
 **/
public class SpiderBaidu {

    @Test
    public void test01() throws UnsupportedEncodingException {

        Map<String, String> param = new HashMap<String, String>();
        param.put("type", String.valueOf(0));
        param.put("date", "20170601");

        String data = HttpClientUtil.get("http://news.baidu.com/n?m=rddata&v=hot_word", param);

        System.out.println(data);
        JSONObject jsonObject= JSON.parseObject(HttpClientUtil.decodeUnicode(data.replace("\\\"","'")));

        //  {"errno":0,"data":[{"title":"三星掌门人被批捕","desc":"三星掌门人李在镕被批捕 被控向崔顺实行...","query_word":"三星掌门人被批捕","image":"http://timg01.baidu-img.cn/timg?tc&size=c378_m272&sec=0&quality=100&di=747e1bd7b66e9f7c9ba4f8357a123073&src=http%3A%2F%2Fnews.youth.cn%2Fjsxw%2F201702%2FW020170217511313131122.jpg","image_v":"http://timg01.baidu-img.cn/timg?tc&size=c184_m272&sec=0&quality=100&di=747e1bd7b66e9f7c9ba4f8357a123073&src=http%3A%2F%2Fnews.youth.cn%2Fjsxw%2F201702%2FW020170217511313131122.jpg"}]}


        if(jsonObject.getInteger("errno")==0){
           JSONArray jsonArray=jsonObject.getJSONArray("data");

           for(int i=0;i<jsonArray.size();i++){

               JSONObject jsonObject1= (JSONObject) jsonArray.get(i);

               System.out.println(jsonObject1.getString("title"));


           }

        }

    }


    @Test
    public void test02() throws IOException {
        String query_word = "43人中国旅行团护照在瑞典被抢";
        String news_base_url = "http://news.baidu.com/ns?tn=news";
        Document document = Jsoup.connect(news_base_url+"&word=AG600水陆两栖飞机成功进行首次滑行")
//                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.86 Safari/537.36")
                .get();
        System.out.println(document.select("div#header_top_bar span.nums").text());
        String numStr=document.select("div#header_top_bar span.nums").text().replace("找到相关新闻","").replace("约","").replace(",","").replace("篇","").trim();
        if(numStr==null||"".equals(numStr)){
            return;
        }
        int num=Integer.parseInt(numStr);
        System.out.println(num);
        int page=num/10;
        if(num%10>0){
            page=page+1;
        }
        int pn=10;
     //   for(int i=0;i<1;i++){
            Document doc = Jsoup.connect(news_base_url+"&word=AG600水陆两栖飞机成功进行首次滑行"+"&pn="+0+"&ct=1&tn=news&ie=utf-8&bt=0&et=0")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.86 Safari/537.36")
                    .get();
            for(Element element:doc.select("div#content_left div div.result  ")){
                System.out.println(element.select("h3 a").attr("href"));
                System.out.println(element.select("h3 a").text());
                System.out.println(element.select("div.c-summary p.c-author").text().split("  ")[0]);
                System.out.println(element.select("div.c-summary p.c-author").text().split("  ")[1]);
            }

     //  }


    }


}
