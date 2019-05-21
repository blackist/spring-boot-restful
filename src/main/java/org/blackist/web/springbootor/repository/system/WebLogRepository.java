package org.blackist.web.springbootor.repository.system;

import org.blackist.web.springbootor.model.entity.system.WebLog;
import org.blackist.web.springbootor.repository.BaseMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebLogRepository extends BaseMongoRepository<WebLog, String> {


}
