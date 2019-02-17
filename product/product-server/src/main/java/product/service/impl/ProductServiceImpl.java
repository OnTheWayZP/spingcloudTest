package product.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import product.dao.ProductInfoRepository;
import product.domain.ProductInfo;
import product.dto.CartDto;
import product.enums.ProductStatusEnum;
import product.enums.ResultEnum;
import product.exception.ProductException;
import product.service.ProductService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findProductByIdS(List<String> ids) {
        return productInfoRepository.findByIdIn(ids);
    }

    @Override
    public void decreaseStock(List<CartDto> cartDtos) {
        if (!CollectionUtils.isEmpty(cartDtos)) {
            cartDtos.forEach(cartDto -> {
                Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDto.getProductId());
                if (!productInfoOptional.isPresent()) {
                    log.error("【商品不存在】，productId={}", cartDto.getProductId());
                    throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                ProductInfo productInfo = productInfoOptional.get();
                Integer num = productInfo.getProductStock() - cartDto.getProductNum();
                if (num < 0) {
                    log.error("【商品库存不足】，productName={},productStock={}", productInfo.getName(), num);
                    throw new ProductException(ResultEnum.STOCK_NOT_ENOUGH);
                }
                productInfo.setProductStock(num);
                productInfoRepository.save(productInfo);
            });
        }
    }
}
