package org.blackist.web.springbootor.repository;

import org.blackist.web.springbootor.model.entity.system.Role;
import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.model.entity2nd.Message;
import org.blackist.web.springbootor.repository.system.RoleRepository;
import org.blackist.web.springbootor.repository.system.UserRepository;
import org.blackist.web.springbootor.repository2nd.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void save() {

        User user = new User();
//        Message message = new Message("name", "content");

        user.setName("Yuti");
        user.setUsername("yuti");
        user.setPassword("123456");
        user.setEnable(true);

        userRepository.save(user);
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
