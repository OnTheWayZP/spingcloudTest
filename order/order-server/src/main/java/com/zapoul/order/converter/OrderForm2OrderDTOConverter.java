package com.zapoul.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zapoul.order.domain.OrderDetail;
import com.zapoul.order.client.dto.OrderDTO;
import com.zapoul.order.enums.ResultEnum;
import com.zapoul.order.exception.OrderException;
import com.zapoul.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetails = new ArrayList<>();

        try {
            orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("【json转换】出错，string={}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }
}
