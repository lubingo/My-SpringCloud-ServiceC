package com.cloud.springcloud.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.springcloud.es.dao.ProductMainMysqlDao;
import com.cloud.springcloud.es.entity.ProductMain;
import com.cloud.springcloud.es.entity.ProductMainMysql;
import com.cloud.springcloud.es.service.ProductMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/6/20 15:05
 * @since
 */
@RestController
@RequestMapping(value = "/es")
public class ProductMainController {

    @Autowired
    private ProductMainMysqlDao productMainMysqlDao ;
    @Autowired
    private ProductMainService productMainService ;
    /**
     * 插入数据到es
     */
    @RequestMapping(value = "/insertToEs" , method={RequestMethod.GET})
    public void  insertToEs(){
        //从数据库查数据
        //传入数据到es
        long count =  productMainMysqlDao.count();
        long pageSize = 100 ;
        long pageConut = count%pageSize==0 ? count/pageSize : count/pageSize +1;
        List<ProductMain> ll = new ArrayList();
        for (int i = 0; i <pageConut ; i++) {
            List<ProductMainMysql>   list = productMainMysqlDao.findAll(  PageRequest.of((int) pageConut*i,(int) pageSize)).getContent();
            for (int j = 0; j <list.size() ; j++) {
                ProductMain productMain = new ProductMain();
                productMain.setActualSize(list.get(j).getActualSize());
                productMain.setAddressUrl(list.get(j).getAddressUrl());
                productMain.setBrand(list.get(j).getBrand());
                productMain.setCreateTime(list.get(j).getCreateTime());
                productMain.setId(list.get(j).getId());
                productMain.setImage(list.get(j).getImage());
                productMain.setInitializeSize(list.get(j).getInitializeSize());
                productMain.setIntro(list.get(j).getIntro());
                productMain.setName(list.get(j).getName());
                productMain.setSort(list.get(j).getSort());
                productMain.setUpdateTime(list.get(j).getUpdateTime());
                productMain.setUserName(list.get(j).getUserName());
                productMain.setUseState(list.get(j).getUseState());

                ll.add(productMain);
            }
            productMainService.saveAll(ll);
        }
    }

    /**
     * 查询Es数据
     */
    @RequestMapping(value = "/serch" ,method = {RequestMethod.GET,RequestMethod.POST})
    public  String serch(int pageIndex ,int size){
        List<ProductMain> list =productMainService.query(pageIndex,size,new String[]{"intro"},new String[]{"金"},new String[]{"100"});
        return  JSONObject.toJSON(list).toString() ;
    }

}
