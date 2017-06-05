package com.yangcb.seckill.service;

import com.yangcb.seckill.dto.ResultModel;
import com.yangcb.seckill.entity.ProductModel;

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
}
