package com.zapoul.order.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class ProductInfo {
    @Id
    private String id;

    private String name;

    private BigDecimal productPrice;

    private Integer productStock;

    private String description;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}