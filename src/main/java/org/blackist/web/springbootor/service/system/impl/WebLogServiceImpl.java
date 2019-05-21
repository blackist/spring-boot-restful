package org.blackist.web.springbootor.service.system.impl;

import org.blackist.web.springbootor.model.entity.system.WebLog;
import org.blackist.web.springbootor.repository.system.WebLogRepository;
import org.blackist.web.springbootor.service.BaseServiceMongoImpl;
import org.blackist.web.springbootor.service.system.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("webLogService")
public class WebLogServiceImpl extends BaseServiceMongoImpl<WebLog, WebLogRepository> implements WebLogService {

    private final WebLogRepository webLogRepository;

    @Autowired
    public WebLogServiceImpl(WebLogRepository webLogRepository) {
        this.webLogRepository = webLogRepository;
    }

    @Override
    public WebLogRepository getRepository() {
        return webLogRepository;
    }

    public void saveLog(WebLog log) {
        webLogRepository.save(log);
    }
}
