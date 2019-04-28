package org.blackist.web.springbootor.common.response;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 1075512174@qq.com.
 * @Date:2019/2/4 15:20.
 */
public class Response {

    private int code;

    private String message;

    private Object data;

    public Response() {

    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response code(int code) {
        this.code = code;
        return Response.this;
    }

    public Response message(String message) {
        this.message = message;
        return Response.this;
    }

    public Response data(Object data) {
        this.data = data;
        return Response.this;
    }

    public Response data(String key, Object value) {
        if (!(data instanceof Data)) {
            this.data = new Data();
        }
        ((Data) data).put(key, value);

        return Response.this;
    }

    public int getCode() {
        return code;
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
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
        return new Response().setData(data).setCode(ErrorCode.OK).setMessage("OK");
    }

    public static Response PARAM_ERROR() {
        return new Response().setCode(ErrorCode.PARAM_ERROR).setMessage("Param Error");
    }

    public static Response PARAM_NULL() {
        return new Response().setCode(ErrorCode.PARAM_NULL).setMessage("Param Null");
    }
}
