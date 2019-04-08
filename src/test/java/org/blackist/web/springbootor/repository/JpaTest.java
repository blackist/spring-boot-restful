package org.blackist.web.springbootor.repository;

import org.blackist.web.springbootor.entity.user.User;
import org.blackist.web.springbootor.web.repository.user.UserRepository;
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

    @Test
    public void save() {

        User user = new User();

        user.setName("black");
        user.setPassword("123456");

        userRepository.save(user);
    }

    @Test
    public void query() {
        List<User> users = userRepository.findAll();
        System.out.println(users.size());
    }
}
