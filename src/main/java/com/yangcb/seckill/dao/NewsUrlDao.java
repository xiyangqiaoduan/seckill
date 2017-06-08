package com.yangcb.seckill.dao;

import com.yangcb.seckill.entity.NewsUrl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-07 13:46
 **/
public interface NewsUrlDao {

    int insert(NewsUrl newsUrl);

    int insertByBatch( List<NewsUrl> newsUrls);

}
