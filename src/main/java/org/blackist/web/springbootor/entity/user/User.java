package org.blackist.web.springbootor.entity.user;

import org.blackist.web.springbootor.entity.BaseEntity;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 1075512174@qq.com.
 * @Date:2019/2/4 15:05.
 */
public class User extends BaseEntity {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
