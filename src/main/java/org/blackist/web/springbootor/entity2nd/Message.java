package org.blackist.web.springbootor.entity2nd;

import org.blackist.web.springbootor.entity.BaseEntity;

import javax.persistence.*;

/**
 * TODO ${TODO}
 *
 * @author LiangLiang.Dong<liangl.dong @ qq.com>
 * @since 2019/4/19 7:41.
 */
@Entity
@Table(name = "message")
@Access(value = AccessType.FIELD)
public class Message extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    public Message(){}

    public Message(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
