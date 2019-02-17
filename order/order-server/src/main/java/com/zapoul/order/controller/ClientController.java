package com.zapoul.order.controller;

import com.zapoul.order.ResultVO;
import com.zapoul.order.client.api.ProductClient;
import com.zapoul.order.domain.ProductInfo;
import com.zapoul.order.client.api.CartDto;
import com.zapoul.order.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

  /*  @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    ProductClient productClient;

    @GetMapping("getProductMsg")
    public String getProductMsg(){
        //1.第一种方式（运用RestTemplate，url写死）
       /* RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/getMsg", String.class);
      */

        //2.第二种方式（运用loadBalancerClient获取url，再用运用RestTemplate）
      /*  RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/getMsg";
        String response = restTemplate.getForObject(url, String.class);
       */

        //第三种方式
       /* String response = restTemplate.getForObject("http://PRODUCT/getMsg", String.class);
        */
        String response = productClient.productMsg();
        log.info("response={}",response);
        return response;
    }

    @GetMapping("/productList")
    public ResultVO<List<ProductInfo>> list(){
        List<String> productIdS = Arrays.asList("164103465734242707");
        List<ProductInfo> list = productClient.list(productIdS);
        log.info("response={}",list);
        return ResultUtil.success( productClient.list(productIdS));
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock(){
        List<CartDto> cartDtos = new ArrayList<>();
        CartDto cartDto = new CartDto();
        cartDto.setProductId("164103465734242707");
        cartDto.setProductNum(2);
        cartDtos.add(cartDto);
        productClient.decreaseStock(cartDtos);
        return "ok";
    }
}
