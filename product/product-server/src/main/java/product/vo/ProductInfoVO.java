package product.vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO extends BaseVO {

    private BigDecimal productPrice;

    private String description;

    private String productIcon;
}
