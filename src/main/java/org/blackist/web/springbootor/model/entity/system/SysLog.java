package org.blackist.web.springbootor.model.entity.system;

import lombok.Data;
import org.blackist.web.springbootor.model.entity.BaseEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "S_SYS_LOG")
@Access(value = AccessType.FIELD)
public class SysLog extends BaseEntity {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;
}
