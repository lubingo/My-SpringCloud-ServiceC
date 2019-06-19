package com.cloud.springcloud.core.annotation;

import java.lang.annotation.*;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/6/17 13:53
 * @since
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PermissionAnotation {

    boolean isCheck() default  true ;

    boolean isAdmin() default  false ;


}
