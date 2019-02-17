package product.vo;

import lombok.Data;

import javax.persistence.Id;

@Data
public class BaseVO {
    @Id
    private String id;

    private String name;

}
