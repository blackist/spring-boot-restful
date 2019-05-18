package org.blackist.web.springbootor.service.system.impl;

import org.blackist.web.springbootor.model.entity.system.SysLog;
import org.blackist.web.springbootor.repository.system.SysLogRepository;
import org.blackist.web.springbootor.service.BaseServiceImpl;
import org.blackist.web.springbootor.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, SysLogRepository> implements SysLogService {

    private final SysLogRepository sysLogRepository;

    @Autowired
    public SysLogServiceImpl(SysLogRepository sysLogRepository) {
        this.sysLogRepository = sysLogRepository;
    }

    @Override
    public SysLogRepository getRepository() {
        return sysLogRepository;
    }

    public void saveLog(SysLog log) {
        sysLogRepository.save(log);
    }
}
