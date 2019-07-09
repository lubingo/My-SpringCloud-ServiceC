package com.cloud.springcloud.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lubing
 * @describe es测试实体类
 * @Date 2019/6/19 18:12
 * @since
 */
@Table(name = "d_product_main")
@Entity
@Data
public class ProductMainMysql {

    @Id
    private Long id;
    private String name;
    private String image;
    private String sort;
    private String intro;
    private String brand;
    private String useState;
    private String createTime;
    private String updateTime;
    private String addressUrl;
    private String userName;
    private String initializeSize;
    private String actualSize;

    @Override
    public String toString() {
        return "XxlJobLog{" +
                "id=" + id +
                ", name=" + name +
                ", image=" + image +
                ", sort='" + sort + '\'' +
                ", intro='" + intro + '\'' +
                ", brand='" + brand + '\'' +
                ", useState='" + useState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", addressUrl='" + addressUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", initializeSize='" + initializeSize + '\'' +
                ", actualSize='" + actualSize + '\'' +
                '}';
    }
}
