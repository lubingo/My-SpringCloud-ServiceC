package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableScheduling
public class SpringcloudApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SpringcloudApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

//    @Scheduled(fixedRate = 1000)
//    public  void  test(){
//        System.out.println("调度器开始..");
//    }
}
