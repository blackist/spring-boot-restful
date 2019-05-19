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

    /**
     * http
     */
    private String requestURL;

    private String requestURI;

    private String queryString;

    private String headers;

    private String remoteAddr;

    private String remoteHost;

    private int remotePort;

    private String localAddr;

    private String localName;

    /**
     * method
     */
    private String classMethod;

    private String params;

    private Long executeTime;

    private String remark;
}
