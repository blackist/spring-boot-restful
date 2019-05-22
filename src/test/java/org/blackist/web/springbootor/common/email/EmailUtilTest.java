package org.blackist.web.springbootor.common.email;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.blackist.web.springbootor.SpringbootorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.StringWriter;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootorApplication.class)
public class EmailUtilTest {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Test
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("liangl.dong@ivisiontech.cn");
        message.setTo("liangl.dong@qq.com");
        message.setSubject("Springbootor测试");
        message.setText("测试");

        mailSender.send(message);
    }

    @Test
    public void sendMailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("liangl.dong@ivisiontech.cn");
        message.setTo("liangl.dong@qq.com");
        message.setSubject("Springbootor测试");

        VelocityContext context = new VelocityContext();
        context.put("username", "blackist");
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("/templates/velocity/mail-template.vm", "UTF-8", context, stringWriter);

        message.setText(stringWriter.toString());

        mailSender.send(message);
    }
}