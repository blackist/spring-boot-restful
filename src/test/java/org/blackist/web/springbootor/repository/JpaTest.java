package org.blackist.web.springbootor.repository;

import org.blackist.web.springbootor.entity.user.User;
import org.blackist.web.springbootor.entity2nd.Message;
import org.blackist.web.springbootor.repository.user.UserRepository;
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

    @Test
    public void save() {

        User user = new User();
        Message message = new Message("name", "content");

        user.setName("yuti");
        user.setPassword("123456");

        userRepository.save(user);
        messageRepository.save(message);
    }

    @Test
    public void query() {
        List<User> users = userRepository.findAll();
        System.out.println(users.size());
    }
}
