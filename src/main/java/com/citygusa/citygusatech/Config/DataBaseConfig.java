package com.citygusa.citygusatech.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/seubanco");
//        dataSource.setUsername("seuusuario");
//        dataSource.setPassword("suasenha");
//        return dataSource;
//    }


    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        managerDataSource.setUrl("jdbc:mysql://localhost:3306/citygusatech");
        managerDataSource.setUsername("root");
        managerDataSource.setPassword("3675");
        return managerDataSource;
    }
}
