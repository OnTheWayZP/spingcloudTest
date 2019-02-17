package product.service;

import org.springframework.web.bind.annotation.RequestBody;
import product.domain.ProductInfo;
import product.dto.CartDto;

import java.util.List;

public interface ProductService {

    List<ProductInfo> findUpAll();

    List<ProductInfo> findProductByIdS(List<String> ids);

    void decreaseStock(@RequestBody List<CartDto> cartDtos);
}
