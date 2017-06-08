package com.yangcb.seckill.dao;

import com.yangcb.seckill.entity.News;

import java.util.List;

/**
 * 新闻信息
 * @author yangcb
 * @create 2017-06-07 13:46
 **/
public interface NewsDao {

    int insert(News news);
    /**
     * 批量插入
     * @param news
     * @return
     */
    int insertByBatch(List<News> news);


}
