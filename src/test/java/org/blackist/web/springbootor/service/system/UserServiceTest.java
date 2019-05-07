package org.blackist.web.springbootor.service.system;

import org.blackist.web.springbootor.SpringbootorApplication;
import org.blackist.web.springbootor.model.entity.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootorApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void insertUsers() {
        User user = new User();
        user.setName("Blackist3");
        user.setUsername("blackist3");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnable(true);

//        User user1 = new User();
//        user1.setName("BlackBlackBlackBlackBlackBlackBlack");
////        user1.setName("Blackist4");
//        user1.setUsername("blackist4");
//        user1.setPassword(passwordEncoder.encode("123456"));
//        user1.setEnable(true);

        List<User> users = new ArrayList<>();
        users.add(user);
//        users.add(user1);

        try {
            userService.insertUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}