package org.blackist.web.springbootor.common.response;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 1075512174@qq.com.
 * @Date:2019/2/4 15:20.
 */
public class Response {

    private int code;

    private String msg;

    private Object data;

    public Response() {

    }

    public int getCode() {
        return code;
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public static Response SUCCESS(Object data) {
        return new Response().setData(data).setCode(ErrorCode.OK).setMsg("OK");
    }

    public static Response PARAM_ERROR() {
        return new Response().setCode(ErrorCode.PARAM_ERROR).setMsg("Param Error");
    }

    public static Response PARAM_NULL() {
        return new Response().setCode(ErrorCode.PARAM_NULL).setMsg("Param Null");
    }
}
