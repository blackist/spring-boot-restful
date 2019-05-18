package org.blackist.web.springbootor.service;

import org.blackist.web.springbootor.SpringbootorApplication;
import org.blackist.web.springbootor.model.security.TokenDetail;
import org.blackist.web.springbootor.service.system.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootorApplication.class)
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void getTokenDetail() {
        TokenDetail tokenDetail = loginService.getTokenDetail("yuti");
        assert tokenDetail != null;
    }

    @Test
    public void generateToken() {
        TokenDetail tokenDetail = loginService.getTokenDetail("yuti");
        String token = loginService.generateToken(tokenDetail);
        assert token != null;
    }
}