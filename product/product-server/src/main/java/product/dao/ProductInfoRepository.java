package product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import product.domain.ProductInfo;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 通过商品状态查询商品列表
     *
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 通过商品id查询商品列表
     *
     * @param ids
     * @return
     */
    List<ProductInfo> findByIdIn(List<String> ids);
}
