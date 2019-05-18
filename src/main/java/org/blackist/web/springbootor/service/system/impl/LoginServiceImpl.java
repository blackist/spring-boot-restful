package org.blackist.web.springbootor.service.system.impl;

import org.blackist.web.springbootor.model.security.TokenDetail;
import org.blackist.web.springbootor.model.security.TokenUtil;
import org.blackist.web.springbootor.repository.system.UserRepository;
import org.blackist.web.springbootor.service.system.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final TokenUtil tokenUtil;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public TokenDetail getTokenDetail(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtil.generateToken(tokenDetail);
    }
}
