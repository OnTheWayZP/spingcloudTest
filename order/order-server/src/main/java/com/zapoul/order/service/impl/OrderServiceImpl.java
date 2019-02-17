package com.zapoul.order.service.impl;

import com.zapoul.order.client.api.ProductClient;
import com.zapoul.order.client.dto.CartDto;
import com.zapoul.order.dao.OrderDetailDao;
import com.zapoul.order.dao.OrderMasterDao;
import com.zapoul.order.domain.OrderDetail;
import com.zapoul.order.domain.OrderMaster;
import com.zapoul.order.domain.ProductInfo;
import com.zapoul.order.client.dto.OrderDTO;
import com.zapoul.order.enums.OrderStatusEnum;
import com.zapoul.order.enums.PayStatusEnum;
import com.zapoul.order.service.OrderService;
import com.zapoul.order.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private ProductClient productClient;

    /**
     *  2.查询商品信息
     *  3.计算总价
     *  4.扣库存
     *  5.订单入库
     */
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        String orderId = KeyUtils.getUniqueKey();

        //查询商品信息
        List<String> productIds = orderDTO.getOrderDetails().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfos = productClient.list(productIds);

        //计算总价
        BigDecimal totalMoney = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail : orderDTO.getOrderDetails()){
            for(ProductInfo productInfo:productInfos){
                if(orderDetail.getProductId().equals(productInfo.getId())){
                    totalMoney =  productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(totalMoney);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setProductName(productInfo.getName());
                    orderDetail.setId(KeyUtils.getUniqueKey());
                    orderDetailDao.save(orderDetail);
                }
            }
        }

        //扣库存
        List<CartDto> cartDtos = orderDTO.getOrderDetails().stream().map(orderDetail ->
            new CartDto(orderDetail.getProductId(), orderDetail.getProductQuantity())
        ).collect(Collectors.toList());
        productClient.decreaseStock(cartDtos);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(totalMoney);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setId(KeyUtils.getUniqueKey());
        orderMasterDao.save(orderMaster);
        orderDTO.setId(orderMaster.getId());
        return orderDTO;
    }
}
