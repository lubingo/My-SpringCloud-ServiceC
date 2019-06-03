package com.cloud.springcloud;

import com.cloud.springcloud.redis.util.RedisUtils;
import com.cloud.springcloud.thread.ExecutorServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private RedisUtils redisUtils;

    private ExecutorService executorService = ExecutorServiceUtil.getInstance().getExecutorService();

    @RequestMapping(value = "/function", method = {RequestMethod.POST})
    public String function(@RequestParam int a, @RequestParam int b) {
        for (int i = 0; i < a; i++) {
            StringBuilder stringBuilder = new StringBuilder("1");
            String str2 = getRan(3, 9) + "";
            String str3 = getRan(0, 9) + "";
            String str4 = getRan(0, 9) + "";
            String str5 = getRan(0, 9) + "";
            String str6 = getRan(0, 9) + "";
            String str7 = getRan(0, 9) + "";
            String str8 = getRan(0, 9) + "";
            String str9 = getRan(0, 9) + "";
            String str10 = getRan(0, 9) + "";
            String str11 = getRan(0, 9) + "";

            stringBuilder.append(str2).append(str3).append(str4).append(str5).append(str6).append(str7).append(str8).append(str9).append(str10).append(str11);
            String strAll = stringBuilder.toString();
            String strlast4 = stringBuilder.substring(7);
            Future future = executorService.submit(new Callable() {
                @Override
                public Object call() {
                    redisUtils.set(strAll, strlast4);
                    System.out.println(redisUtils.get(strAll));
                    return null;
                }
            });
        }
        return "OK";
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST})
    public String sysout() {
        return  redisUtils.getKeyValue(redisUtils.gtAllKey());
    }

    private static int getRan(int min, int max) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        return random.nextInt(min, max);
    }


}
