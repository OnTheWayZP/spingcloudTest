package product.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @param <T>
 */
@Data
public class ResultVO<T> {

    //返回消息提示
    private String msg;

    //返回消息编码
    private Integer code;

    private T data;

}
