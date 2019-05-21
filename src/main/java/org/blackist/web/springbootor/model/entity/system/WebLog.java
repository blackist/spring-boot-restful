package org.blackist.web.springbootor.model.entity.system;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "weblog")
public class WebLog {

    @Id
    private String id;

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

    private Date createTime;
}
