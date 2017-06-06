package com.yangcb.seckill.service;

import java.util.List;
import com.yangcb.seckill.dto.ResultModel;
import com.yangcb.seckill.entity.ProductModel;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * 商品模块Serice层接口
 *
 * @author yangcb
 * @create 2017-06-05 14:26
 **/
public interface ProductService {

    /**
     * 根据各种检索条件去调用dao层的方法，得到符合检索条件的数据：
     * @param queryString
     * @param catalog_name
     * @param price
     * @param page
     * @param sort
     * @return
     */
    ResultModel<ProductModel> search(String queryString, String catalog_name, String price, Integer page, String sort) throws Exception;

    /**
     * 根据产品的id获取相似度的产品
     * @param pid 产品id
     * @param count 产品条数
     * @return
     */
    List<ProductModel> getRelatedProduct(String pid, int count) throws SolrServerException;
}
