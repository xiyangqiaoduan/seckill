package com.yangcb.seckill.web;



import com.yangcb.seckill.dto.ResultModel;
import com.yangcb.seckill.entity.ProductModel;
import com.yangcb.seckill.service.ProductService;
import com.yangcb.seckill.service.SpiderService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 商品模块检索
 *
 * @author yangcb
 * @create 2017-06-05 14:24
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SpiderService spiderService;

    @ResponseBody
    @RequestMapping(value = "list",produces ={"application/json;charset=utf-8"})
    /**
     * 检索商品信息
     */
    public ResultModel productList(String queryString, String catalog_name, String price, Integer page, String sort, Model model) {

        ResultModel<ProductModel> resultModel= null;
        try {
            resultModel = productService.search(queryString,catalog_name,price,page,sort);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultModel;
    }


    @ResponseBody
    @RequestMapping(value = "related",produces ={"application/json;charset=utf-8"})
    /**
     * 获取相关的产品
     */

    /**
     * pid :产品id
     * count：指定返回的条数
     */
    public List<ProductModel> getRelatedProduct(String pid, int count ) throws SolrServerException {
        List<ProductModel> list= productService.getRelatedProduct(pid,count);

        return list;
    }
    @ResponseBody
    @RequestMapping(value = "spider",produces ={"application/json;charset=utf-8"})
    public String spider() throws Exception {
        spiderService.spiderBaidu();

        return "success";
    }




}
