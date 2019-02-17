package product.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import product.domain.ProductCategory;
import product.domain.ProductInfo;
import product.dto.CartDto;
import product.service.CategoryService;
import product.service.ProductService;
import product.utils.ResultUtil;
import product.vo.ProductInfoVO;
import product.vo.ProductVO;
import product.vo.ResultVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1.查询上架商品列表
     * 2.查询类别列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list() {
        //获取所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //获取类目列表
        List<Integer> categoryTypes = productInfoList.stream()
                .map(ProductInfo::getCategoryType).collect(Collectors.toList());

        //查询类目
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypes);

        List<ProductVO> productVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categoryTypes)) {
            productCategories.forEach(productCategory -> {
                ProductVO productVO = new ProductVO();
                productVO.setCategoryType(productCategory.getCategoryType());
                productVO.setName(productCategory.getName());

                List<ProductInfoVO> productInfoVOS = new ArrayList<>();
                if (!CollectionUtils.isEmpty(categoryTypes)) {
                    productInfoList.forEach(productInfo -> {
                        if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                            ProductInfoVO productInfoVO = new ProductInfoVO();
                            BeanUtils.copyProperties(productInfo, productInfoVO);
                            productInfoVOS.add(productInfoVO);
                        }
                    });
                }
                productVO.setProductInfoVOS(productInfoVOS);
                productVOS.add(productVO);
            });
        }
        return ResultUtil.success(productVOS);
    }

    @PostMapping("/listForOrder")
    public List<ProductInfo> list(@RequestBody List<String> productIdS) {
        return productService.findProductByIdS(productIdS);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDto> cartDtos) {
        productService.decreaseStock(cartDtos);
    }
}
