package product.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UserInfo {
    @Id
    private String id;

    private String name;

    private String password;

    private String openid;

    private Boolean role;

    private Date createTime;

    private Date updateTime;

}