package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
@MapperScan("com.example.demo.mapper")
public class MyBatisConfig {

    @Value("${oracle.driver}")
    private String driver;

    @Value("${oracle.url}")
    private String url;

    @Value("${oracle.username}")
    private String username;

    @Value("${oracle.password}")
    private String password;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource dataSource) throws Exception {

        SqlSessionFactoryBean factory
                = new SqlSessionFactoryBean();

        factory.setDataSource(dataSource);

        factory.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/*.xml")
        );

        return factory.getObject();
    }
}
