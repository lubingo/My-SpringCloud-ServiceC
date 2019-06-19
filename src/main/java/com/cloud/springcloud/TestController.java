package com.cloud.springcloud;

import com.cloud.springcloud.core.annotation.PermissionAnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PermissionAnotation
    @RequestMapping(value = "/function", method = {RequestMethod.POST})
    public String function(@RequestParam String email  ) {

    return  "测试controller";
    }


}
