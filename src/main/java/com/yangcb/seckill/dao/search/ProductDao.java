package com.yangcb.seckill.dao.search;

import com.yangcb.seckill.dto.ResultModel;
import com.yangcb.seckill.entity.ProductModel;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.SimpleOrderedMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    public void delAll() throws IOException, SolrServerException {

        solrServer.deleteByQuery("*:*");

        solrServer.commit();

    }


    public ResultModel<ProductModel> search(SolrQuery solrQuery) throws Exception {
        //1、查询并获取响应
        QueryResponse response = solrServer.query(solrQuery);
        //2、从响应中获取结果集
        SolrDocumentList results = response.getResults();

        //获取高亮信息
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();


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
                //商品名称
                List list = highlighting.get(document.get("id")).get("product_name");

                if (list != null) {
                    product.setName((String) list.get(0));
                } else {
                    //商品名称：
                    product.setName(String.valueOf(document.get("product_name")));
                }

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


    /**
     * 相似度查询
     *
     * @param solrQuery
     */
    public List<ProductModel> getRelatedProduct(SolrQuery solrQuery) throws SolrServerException {

        List<ProductModel> list = new ArrayList<ProductModel>();
        QueryResponse response = solrServer.query(solrQuery);
        if (response == null) {
            return list;
        }
        //获取相似度id，
        String id=solrQuery.get("q").split(":")[1];
        SimpleOrderedMap<SolrDocumentList> mltResults = (SimpleOrderedMap<SolrDocumentList>) response.getResponse().get("moreLikeThis");
        for (int i = 0; i < mltResults.size(); i++) {
            SolrDocumentList items = mltResults.getVal(i);
            for (SolrDocument doc : items) {
                String idStr = doc.getFieldValue("id").toString();
                if(idStr.equals(id)) continue;//如果是本身就继续， 排除本身
                ProductModel productModel=new ProductModel();
                productModel.setPid(idStr);
                productModel.setName(doc.getFieldValue("product_name").toString());
                list.add(productModel);
            }
        }
        return list;
    }
}
