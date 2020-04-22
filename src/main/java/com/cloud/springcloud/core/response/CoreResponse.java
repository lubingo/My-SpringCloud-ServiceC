package com.cloud.springcloud.core.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Response返回类 对象 ，支持泛型传入
 * @param <T>
 * @Auther lubing
 */

@Data
public class CoreResponse<T>  implements Serializable  {
    private  int   code  ;
    private  String  message ;
    private  T  t  ;

    public CoreResponse() {

    }
    public CoreResponse(int   code ,String  message) {

        this.code = code;
        this.message=message;
    }
    public CoreResponse(int   code ,String  message ,T t) {

        this.code = code ;
        this.message = message ;
        this.t=t;
    }
}
