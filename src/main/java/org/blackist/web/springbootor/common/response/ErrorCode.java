package org.blackist.web.springbootor.common.response;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 1075512174@qq.com.
 * @Date:2019/2/4 15:27.
 */
public interface ErrorCode {

    int OK = 0;

    int HTTP_400 = 400;
    int HTTP_500 = 500;

    int PARAM_NULL = 1000;
    int PARAM_ERROR = 1001;

    int DATA_NULL = 1100;
}
