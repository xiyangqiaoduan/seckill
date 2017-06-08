package com.yangcb.seckill.test;

import com.yangcb.seckill.dao.search.ProductDao;
import com.yangcb.seckill.entity.ProductModel;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-06 10:36
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SolrTest {


    @Autowired
    private ProductDao productDao;

    @Test
    public  void  add() throws Exception {

        ProductModel productModel=new ProductModel();
        productModel.setPicture("https://img11.360buyimg.com/mobilecms/s250x250_g16/M00/01/07/rBEbRVNrJScIAAAAAAEkuI5nWmsAAAPfgDIQl0AASTQ615.jpg");
        productModel.setPrice(4598.00f);
        productModel.setCatalog_name("戴尔");
        productModel.setDescription("商品名称：戴尔游匣商品编号：2916342商品毛重：3.97kg商品产地：中国大陆系统：Windows 10屏幕尺寸：15.6英寸硬盘容量：128G+500G游戏性能：发烧级处理器：Intel i5标准电压版特性：其他内存容量：4G裸机重量：大于2.5KG显卡型号：GTX960M显存容量：4G分辨率：全高清屏（1920×1080）待机时长：7-9小时");
        productModel.setName("戴尔DELL灵越游匣15PR-2648B 15.6英寸游戏笔记本电脑(i5-6300HQ 4G 128SSD+500G GTX960M 4G独显 FHD)黑");
        productModel.setPid("2916343");


        //1、文档对象
        SolrInputDocument doc=new SolrInputDocument();
        doc.addField("id",productModel.getPid());
        doc.addField("product_name",productModel.getName());
        doc.addField("product_picture",productModel.getPicture());
        doc.addField("product_price",productModel.getPrice());
        doc.addField("product_catalog_name",productModel.getCatalog_name());

        Collection<SolrInputDocument> docs=new ArrayList<SolrInputDocument>();
        docs.add(doc);
        productDao.add(docs);

    }


    @Test
    public void test02() throws IOException, SolrServerException {

        productDao.delAll();

    }


}
