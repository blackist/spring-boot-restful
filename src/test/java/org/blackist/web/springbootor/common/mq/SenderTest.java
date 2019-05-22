package org.blackist.web.springbootor.common.mq;

import org.blackist.web.springbootor.SpringbootorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootorApplication.class)
public class SenderTest {

    @Autowired
    private Sender sender;

    @Test
    public void send() {
        sender.send();
    }
}