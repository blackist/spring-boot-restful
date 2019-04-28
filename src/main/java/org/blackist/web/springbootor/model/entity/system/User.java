package org.blackist.web.springbootor.model.entity.system;

import org.blackist.web.springbootor.model.security.TokenDetail;
import org.blackist.web.springbootor.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 1075512174@qq.com.
 * @Date:2019/2/4 15:05.
 */
@Entity
@Table(name = "S_USER")
@Access(value = AccessType.FIELD)
public class User extends BaseEntity implements TokenDetail {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private int sex;

    @Column
    private boolean enable;

    @Transient
    private String authority;

    @OneToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
