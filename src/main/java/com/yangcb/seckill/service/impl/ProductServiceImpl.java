package com.yangcb.seckill.service.impl;

import com.yangcb.seckill.dao.search.ProductDao;
import com.yangcb.seckill.dto.ResultModel;
import com.yangcb.seckill.entity.ProductModel;
import com.yangcb.seckill.service.ProductService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 根据参数拼接查询条件，调用dao层方法，查询商品数据
 *
 * @author yangcb
 * @create 2017-06-05 16:30
 **/
@Service
public class ProductServiceImpl implements ProductService {

    private final static Integer PAGE_SIZE = 60;

    @Autowired
    private ProductDao productDao;

    @Override
    public ResultModel<ProductModel> search(String queryString, String catalog_name, String price, Integer page, String sort) throws Exception {

        //1、封装查询条件对象，因为需要调用dao层的方法，dao层的检索方法就需要一个solr服务的查询条件对象
        SolrQuery solrQuery = new SolrQuery();
        //2、设置默认查询的域(该默认的域已经在solrHome/collection1/conf/schema.xml配置文件中配置了业务域)
        solrQuery.setQuery("product_keywords");

        //3、设置关键字查询
        if (queryString != null && !"".equals(queryString)) {
            solrQuery.setQuery(queryString);
        } else {
            //如果没有查询的关键字，则默认查询所有商品数据：
            solrQuery.setQuery("*:*");
        }
        //4、根据商品分类进行过滤：
        if (catalog_name != null && !"".equals(catalog_name)) {
            //注意查询语法：不要忽略":"号
            solrQuery.addFilterQuery("product_catalog_name:" + catalog_name);
        }
        //5、根据商品价格进行过滤：
        if (price != null && !"".equals(price)) {
            //因为传递过来的的商品价格的格式为：100-300，所以需要切割并根据价格区间过滤商品数据：
            String[] split = price.split("-");

            if (split != null && split.length > 1) {
                //为什么判断长度必须大于1，因为根据价格过滤需要最小值和最大值，必须有俩个值才能过滤成功
                solrQuery.addFilterQuery("product_price:[" + split[0] + " TO " + split[1] + "]");
            }

        }
        //6、按照商品价格排序展示商品数据：

        if ("1".equals(sort)) {
            solrQuery.setSort("product_price", SolrQuery.ORDER.asc);
        } else {
            solrQuery.setSort("product_price", SolrQuery.ORDER.desc);
        }
        //7、分页查询商品数据：
        //首先校验数据合法性，如果当前页的值为空或小于1，则默认开始查询第一页数据：
        if (page == null) {
            page = 1;
        }
        if (page < 1) {
            page = 1;
        }
        //计算起始索引
        Integer startIndex=(page-1)*PAGE_SIZE;
        //设置其实索引
        solrQuery.setStart(startIndex);
        //设置每页显示的商品记录数
        solrQuery.setRows(PAGE_SIZE);
        //根据封装后的SolrQuery查询对象查询商品数据：
        ResultModel<ProductModel> resultModel=productDao.search(solrQuery);
        resultModel.setCurPage(page);
        //计算总页数：
        Long pageCount = (long) Math.ceil((resultModel.getRecordCount()*1.0) / PAGE_SIZE);
        resultModel.setPageCount(pageCount);
        return resultModel;
    }
}
