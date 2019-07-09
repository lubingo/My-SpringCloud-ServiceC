package com.cloud.springcloud.es.dao;

import com.cloud.springcloud.es.entity.ProductMainMysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/6/20 14:58
 * @since
 */
@Service
public interface ProductMainMysqlDao extends JpaRepository<ProductMainMysql, Long> {
}
