package org.blackist.web.springbootor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(
//        exclude = {
//                DataSourceAutoConfiguration.class,
//                HibernateJpaAutoConfiguration.class,
//                DataSourceTransactionManagerAutoConfiguration.class
//        })
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableAspectJAutoProxy
public class SpringbootorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootorApplication.class, args);

    }

}

