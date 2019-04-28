package org.blackist.web.springbootor.model.entity.system;

import org.blackist.web.springbootor.model.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "S_ROLE")
@Access(value = AccessType.FIELD)
public class Role extends BaseEntity {

    public Role() {
    }

    public Role(String name, String value, String auth) {
        this.name = name;
        this.value = value;
        this.auth = auth;
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String value;

    @Column(nullable = false)
    private String auth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
