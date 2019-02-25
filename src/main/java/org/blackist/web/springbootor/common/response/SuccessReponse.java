package org.blackist.web.springbootor.common.response;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 1075512174@qq.com.
 * @Date:2019/2/4 15:26.
 */
public class SuccessReponse extends Response {

    public SuccessReponse(Object data) {
        this.setData(data);
        this.setCode(ErrorCode.OK);
        this.setMessage("OK");
    }
}
