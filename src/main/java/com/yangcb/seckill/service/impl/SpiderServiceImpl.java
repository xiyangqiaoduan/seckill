package com.yangcb.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yangcb.seckill.dao.NewsDao;
import com.yangcb.seckill.dao.NewsUrlDao;
import com.yangcb.seckill.dao.search.ProductDao;
import com.yangcb.seckill.entity.News;
import com.yangcb.seckill.entity.NewsUrl;
import com.yangcb.seckill.service.SpiderService;
import com.yangcb.seckill.util.HttpClientUtil;
import com.yangcb.seckill.util.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-07 14:17
 **/
@Service
public class SpiderServiceImpl implements SpiderService {


    public static String BAIDU_URL = "http://news.baidu.com/n?m=rddata&v=hot_word";
    public static String BAIDU_SEARCH_URL = "http://news.baidu.com/ns?tn=news";

    public static String START_DATE = "20150101";


    @Autowired
    private NewsDao newsDao;
    @Autowired
    private NewsUrlDao newsUrlDao;
    @Autowired
    private ProductDao productDao;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void spiderBaidu() throws Exception {

        for (int i = 0; i < 15; i++) {
            if (i == 9 || i == 7 || i == 11 || i == 13) {
                continue;
            }
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("type", String.valueOf(finalI));
                        String date = START_DATE;
                        while (true) {
                            param.put("date", date);
                            String data = HttpClientUtil.get("http://news.baidu.com/n?m=rddata&v=hot_word", param);

                            log.debug("获取文章列表：data={}", data);


                            if (StringUtils.isEmpty(data)) {
                                break;
                            }
                            JSONObject jsonObject = JSON.parseObject(HttpClientUtil.decodeUnicode(data.replace("\\\"", "'")));
                            if (jsonObject.getInteger("errno") == 0) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                List<News> newsList = new ArrayList<News>(jsonArray.size());
                                for (int j = 0; j < jsonArray.size(); j++) {
                                    JSONObject object = (JSONObject) jsonArray.get(j);
                                    News news = new News();
                                    news.setTitle(object.getString("title"));
                                    news.setDesc(object.getString("desc"));
                                    news.setType(finalI);
                                    news.setId(SnowflakeIdWorker.getID(1, 1));
                                    news.setQuery_word(object.getString("query_word"));
                                    news.setImage(object.getString("image"));
                                    news.setImage(object.getString("image_v"));
                                    log.debug("获取文章列表：news={}", JSON.toJSONString(news));
//                        List<NewsUrl> newsUrls=getNewsURLList(object.getString("query_word"),news);
//                        if(newsUrls!=null&&!newsUrls.isEmpty()){
//                            newsUrlDao.insertByBatch(newsUrls);
//                        }

                                    newsList.add(news);
                                }
                                newsDao.insertByBatch(newsList);
                                productDao.add(solr(newsList));
                            }


                            date = sdf.format(getNextDay(sdf.parse(date)));

                            log.debug("当前时间：date={}", date);

                            if (getNextDay(sdf.parse(date)).getTime() > sdf.parse(sdf.format(new Date())).getTime()) {
                                break;
                            }

                        }
                    } catch (Exception e) {
                        log.error("error:", e);
                    }
                }
            }).start();

        }

    }

    public Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }


    private List<NewsUrl> getNewsURLList(String query_word, News news) throws Exception {
        List<NewsUrl> newsUrls = new ArrayList<NewsUrl>();

        Document document = Jsoup.connect(BAIDU_SEARCH_URL + "&word=" + query_word)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.86 Safari/537.36")
                .get();
        System.out.println(document.select("div#header_top_bar span.nums").text());
        String numStr = document.select("div#header_top_bar span.nums").text().replace("找到相关新闻", "").replace("约", "").replace(",", "").replace("篇", "").trim();
        if (numStr == null || "".equals(numStr)) {
            return newsUrls;
        }
        int num = Integer.parseInt(numStr);
        log.debug("查询所有的条数：num={}", num);
        int page = num / 10;
        if (num % 10 > 0) {
            page = page + 1;
        }
        int pn = 10;

        for (int i = 0; i < page; i++) {
            Document doc = Jsoup.connect(BAIDU_SEARCH_URL + "&word=" + query_word + "&pn=" + (i * pn) + "&ct=1&tn=news&ie=utf-8&bt=0&et=0")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.86 Safari/537.36")
                    .get();
            for (Element element : doc.select("div#content_left div div.result  ")) {

                NewsUrl newsUrl = new NewsUrl();

                newsUrl.setTitle(element.select("h3 a").text());
                newsUrl.setNews_id(news.getId());
                newsUrl.setSource(element.select("div.c-summary p.c-author").text().split("  ")[0]);
                newsUrl.setUrl(element.select("h3 a").attr("href"));
                newsUrl.setId(SnowflakeIdWorker.getID(1, 1));

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                    newsUrl.setDate(sdf.parse(element.select("div.c-summary p.c-author").text().split("  ")[1]));
                } catch (Exception e) {
                    newsUrl.setDate(new Date());
                }
                log.debug("查询列表：newsUrl={}", JSON.toJSONString(newsUrl));
                newsUrls.add(newsUrl);
            }

        }

        return newsUrls;

    }


    private List<SolrInputDocument> solr(List<News> newss) {
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (News news : newss) {
            //1、文档对象
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", news.getId());
            doc.addField("news_title", news.getTitle());
            doc.addField("news_description", news.getDesc());
            doc.addField("news_image", news.getImage());
            doc.addField("news_image_v", news.getImage_v());
            doc.addField("news_query_word", news.getQuery_word());
            doc.addField("news_type", news.getType());
            docs.add(doc);
        }
        return docs;
    }


}
