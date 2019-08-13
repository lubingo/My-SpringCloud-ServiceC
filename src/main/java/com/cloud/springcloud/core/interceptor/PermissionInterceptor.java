package com.cloud.springcloud.core.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.cloud.springcloud.core.annotation.PermissionAnotation;
import com.cloud.springcloud.core.response.CoreResponse;
import com.cloud.springcloud.login.entity.Login;
import com.cloud.springcloud.login.service.LoginService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @描述 拦截器：权限控制验证
 * @Auth lubing
 * @Date 2019/06/17
 */
public class PermissionInterceptor  extends HandlerInterceptorAdapter {


    public LoginService loginService ;
    public PermissionInterceptor(LoginService loginService){
        this.loginService = loginService ;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof  HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            boolean isCheck =false ,isAdmin=false;
            if(handlerMethod.hasMethodAnnotation(PermissionAnotation.class)){
                isCheck = handlerMethod.getMethodAnnotation(PermissionAnotation.class).isCheck() ;
                isAdmin = handlerMethod.getMethodAnnotation(PermissionAnotation.class).isAdmin() ;
            }

            if(isCheck){
                String email = request.getParameter("email") ;
                Login login =  loginService.existLoginUser(email);
                if(login==null) {
                    CoreResponse coreResponse = new CoreResponse();
                    coreResponse.setCode(-1);
                    coreResponse.setMessage("该用户["+email+"]不存在");
                    JSONObject jsonObject = (JSONObject) JSONObject.toJSON(coreResponse);
                    PrintWriter writer = response.getWriter() ;
                    writer.print(jsonObject);
                    writer.close();
                    response.setContentType("application/json;charset=UTF-8");
                    return false ;
                }

            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
