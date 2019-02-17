package product.vo;


import java.util.List;

public class ProductVO extends BaseVO {

    private Integer categoryType;

    private List<ProductInfoVO> productInfoVOS;

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVO> getProductInfoVOS() {
        return productInfoVOS;
    }

    public void setProductInfoVOS(List<ProductInfoVO> productInfoVOS) {
        this.productInfoVOS = productInfoVOS;
    }
}
