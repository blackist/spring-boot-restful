package org.blackist.web.springbootor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * TODO ${TODO}
 *
 * @author LiangLiang.Dong [liangl.dong@qq.com]
 * @since 2019/4/15 23:40.
 */
//@Configuration
//@PropertySource({"classpath:application.properties"})
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManagerFactoryFirst",
//        transactionManagerRef = "transactionManagerFirst",
//        basePackages = {"org.blackist.web.springbootor.repository"}
//)
public class PrimaryConfig {


    @Autowired
    private Environment env;

    @Bean(name = "firstDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.first.configuration")
    public DataSource firstDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("user.jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));


        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryFirst")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(firstDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("org.blackist.web.springbootor.entity");

        return factoryBean;
    }


    @Primary
    @Bean(name = "transactionManagerFirst")
    public PlatformTransactionManager transactionManagerPrimary(
            @Qualifier("entityManagerFactoryFirst") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
