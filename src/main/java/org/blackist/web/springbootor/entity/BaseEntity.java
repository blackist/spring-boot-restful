package org.blackist.web.springbootor.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 1075512174@qq.com.
 * @Date:2019/2/4 15:07.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    protected Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
