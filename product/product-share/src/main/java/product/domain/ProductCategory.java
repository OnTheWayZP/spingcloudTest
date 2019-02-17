package product.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String name;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}