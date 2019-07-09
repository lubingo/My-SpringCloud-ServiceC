package com.cloud.springcloud.es.service;

import com.cloud.springcloud.es.entity.ProductMain;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/6/20 9:47
 * @since
 */
@Service
public interface ProductMainService {


    ProductMain saveOrUpdate(ProductMain ProductMain);

    void  saveAll(List<ProductMain> list);

    void delete(ProductMain ProductMain);
    ProductMain queryById(Long id);

    /**
     * 根据传入的属性名及权重比进行查询，分页查询，属性数组与数值数组与权重数组应保持长度一样
     * @param pageIndex
     * @param size
     * @param names
     * @param weight
     * @return
     */
    List<ProductMain>  query(int pageIndex, int size, String[] names,String[] value , String[] weight)  ;


}
