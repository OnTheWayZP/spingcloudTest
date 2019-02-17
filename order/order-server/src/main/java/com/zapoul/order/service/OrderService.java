package com.zapoul.order.service;

import com.zapoul.order.client.dto.OrderDTO;

public interface OrderService {

    /**
     * 新增订单详情
     * @param orderDTO
     */
    OrderDTO save(OrderDTO orderDTO);
}
