package org.blackist.web.springbootor.repository;

import org.blackist.web.springbootor.SpringbootorApplication;
import org.blackist.web.springbootor.model.entity.system.Role;
import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.repository.system.RoleRepository;
import org.blackist.web.springbootor.repository.system.UserRepository;
import org.blackist.web.springbootor.repository2nd.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootorApplication.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void save() {

        User user = new User();
//        Message message = new Message("name", "content");

//        user.setName("BlackBlackBlackBlackBlackBlackBlack");
        user.setName("Blackist");
        user.setUsername("blackist");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnable(true);

        User user1 = new User();
//        user.setName("BlackBlackBlackBlackBlackBlackBlack");
        user1.setName("Yuti");
        user1.setUsername("yuti");
        user1.setPassword(passwordEncoder.encode("123456"));
        user1.setEnable(true);

        userRepository.save(user);
        userRepository.save(user1);
//        messageRepository.save(message);
    }

    @Test
    public void query() {
        List<User> users = userRepository.findAll();
        System.out.println(users.size());
    }

    @Test
    public void test() {
        User user = userRepository.findUserByUsername("yuti");
        System.out.println(user.getName());
    }

    @Test
    public void testRole() {
        Role role = new Role("Admin", "administrator", "home,news");
        role = roleRepository.save(role);

        User user = userRepository.findUserByUsername("yuti");
        user.setRole(role);

        userRepository.saveAndFlush(user);
    }
}
