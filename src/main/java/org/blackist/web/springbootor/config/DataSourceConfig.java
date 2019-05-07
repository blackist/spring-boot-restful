package org.blackist.web.springbootor.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * TODO ${TODO}
 *
 * @author LiangLiang.Dong<liangl.dong                               @                               qq.com>
 * @since 2019/4/14 15:21.
 */
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasourcePrimary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/springbootor")
                .username("root")
                .password("root")
                .build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource2nd")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/springbootor2nd")
                .username("root")
                .password("root")
                .build();
    }
}
