package com.zapoul.order.client.api;

import com.zapoul.order.client.dto.CartDto;
import com.zapoul.order.domain.ProductInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * name 访问哪个应用的接口
 */
@FeignClient(name = "productClient")
public interface ProductClient {

    @GetMapping("/getMsg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> list(@RequestBody List<String> productIdS);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDto> cartDtos);
}
