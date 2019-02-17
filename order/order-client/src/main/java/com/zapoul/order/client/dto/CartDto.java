package com.zapoul.order.client.dto;

public class CartDto {

    private String productId;

    private Integer productNum;


    public CartDto(String productId, Integer productNum) {
        this.productId = productId;
        this.productNum = productNum;
    }
    public CartDto() {
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}
