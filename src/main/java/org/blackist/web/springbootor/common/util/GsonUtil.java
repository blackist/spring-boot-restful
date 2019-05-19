package org.blackist.web.springbootor.common.util;

import com.google.gson.Gson;

/**
 * TODO ${TODO}
 *
 * @author LiangLiang.Dong<liangl.dong @ qq.com>
 * @since 2019/5/19 23:54.
 */
public class GsonUtil {

    private static class GsonHolder{
        private static final Gson  INSTANCE = new Gson();
    }

    public static Gson getInstance() {
        return GsonHolder.INSTANCE;
    }
}
