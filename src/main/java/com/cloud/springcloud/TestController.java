package com.cloud.springcloud;

import com.cloud.springcloud.core.annotation.PermissionAnotation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/test")
public class TestController {

   // @PermissionAnotation
    @RequestMapping(value = "/function" )
    public ModelAndView function(  @RequestParam String email  ) {
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/login/loginSystem");
        model.getModel().put("email" ,"15810172080@139.com");
        model.getModel().put("strPassword" ,"123456");

    return  model;
    }

    @RequestMapping(value = "/function1"  )
    public String function1(   ) {
        System.out.println("重定向的方法！");
        return "" ;
    }

}
