package com.zapoul.order.controller;

import com.zapoul.order.ResultVO;
import com.zapoul.order.converter.OrderForm2OrderDTOConverter;
import com.zapoul.order.client.dto.OrderDTO;
import com.zapoul.order.enums.ResultEnum;
import com.zapoul.order.exception.OrderException;
import com.zapoul.order.form.OrderForm;
import com.zapoul.order.service.OrderService;
import com.zapoul.order.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {



    @Autowired
    private OrderService orderService;

    /**
     * 1.校验参数
     * 2.查询商品信息
     * 3.计算总价
     * 4.扣库存
     * 5.订单入库
     */
    @RequestMapping("add")
    public ResultVO add(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO save = orderService.save(orderDTO);
        return ResultUtil.success(save.getId());
    }
}
