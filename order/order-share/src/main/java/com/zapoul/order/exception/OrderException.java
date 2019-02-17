package com.zapoul.order.exception;

import com.zapoul.order.enums.ResultEnum;

public class OrderException extends RuntimeException{

    private Integer code;

    public OrderException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
