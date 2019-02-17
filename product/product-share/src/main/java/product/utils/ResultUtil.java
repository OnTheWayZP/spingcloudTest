package product.utils;

import product.vo.ResultVO;

public class ResultUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
