package product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import product.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, String> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
