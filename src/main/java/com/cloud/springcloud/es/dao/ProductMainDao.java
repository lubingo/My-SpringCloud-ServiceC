package com.cloud.springcloud.es.dao;

import com.cloud.springcloud.es.entity.ProductMain;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/6/20 9:38
 * @since
 */
@Repository
public interface ProductMainDao extends ElasticsearchRepository<ProductMain ,Long > {


}
