package org.blackist.web.springbootor.service.system;


import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<User> {

    void insertUsers(List<User> users) throws Exception;
}
