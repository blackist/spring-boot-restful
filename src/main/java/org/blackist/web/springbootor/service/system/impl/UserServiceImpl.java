package org.blackist.web.springbootor.service.system.impl;

import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.repository.system.UserRepository;
import org.blackist.web.springbootor.service.BaseServiceImpl;
import org.blackist.web.springbootor.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Transactional(isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    @Override
    public void insertUsers(List<User> users) {
        userRepository.saveAll(users);
    }
}
