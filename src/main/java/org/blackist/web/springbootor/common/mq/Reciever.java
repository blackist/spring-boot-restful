package org.blackist.web.springbootor.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "hello")
public class Reciever {

    @RabbitHandler
    public void process(String hello) {
        log.debug(hello);
    }
}
