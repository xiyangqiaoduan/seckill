package com.yangcb.seckill.dao.search;

import com.yangcb.seckill.dto.ResultModel;
import com.yangcb.seckill.entity.ProductModel;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-05 16:47
 **/
public class ProductDao {

    private CloudSolrServer solrServer;


    public void add(Collection<SolrInputDocument> docs) throws Exception {
        solrServer.add(docs);
        solrServer.commit();
    }


    public ResultModel<ProductModel> search(SolrQuery solrQuery) throws Exception {
        //1、查询并获取响应
        QueryResponse response = solrServer.query(solrQuery);
        //2、从响应中获取结果集
        SolrDocumentList results = response.getResults();
        //3、处理结果集：
        //专门用于存放响应结果集中的个个商品数据的集合
        List<ProductModel> productList = new ArrayList<ProductModel>();
        //用于将检索到的并处理好的数据封装到ResultModel对象中，用于返回给Service层：
        ResultModel resultModel = new ResultModel();
        //做非空校验，如果检索到的结果集不为空的话，就进行处理并封装结果数据：
        if (results != null) {
            //(1)、封装查询到的商品总数：
            resultModel.setRecordCount(results.getNumFound());
            //(2)、遍历响应中的结果集，并将数据一一封装进ProductModel对象中：
            for (SolrDocument document : results) {
                //创建ProductModel对象：
                ProductModel product = new ProductModel();
                //商品id：
                product.setPid(String.valueOf(document.get("id")));
                //商品名称：
                product.setName(String.valueOf(document.get("product_name")));
                //商品价格：

                if (document.get("product_price") != null && String.valueOf(document.get("product_price")) != null && !"".equals(String.valueOf(document.get("product_price")))) {
                    product.setPrice(Float.valueOf(String.valueOf(document.get("product_price"))));
                }
                //商品图片
                product.setPicture(String.valueOf(document.get("product_picture")));
                //将当前遍历并封装好的ProductModel对象添加到存放商品数据的集合中：
                productList.add(product);

            }
            //循环处理并封装好结果集之后，将存放商品数据的集合设置到ResultModel对象中：
            resultModel.setList(productList);

        }
        //将封装好的数据返回给Service层处理：
        return resultModel;
    }


    public void setSolrServer(CloudSolrServer solrServer) {
        this.solrServer = solrServer;
    }

}
