package com.spring.Config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@ComponentScan(value ="com.spring")
@PropertySource("classpath:application.properties")


public class HibernateConfig {
    @Resource
    @Autowired
    private Environment environment;


    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        LocalSessionFactoryBean factoryBean=new LocalSessionFactoryBean();
        factoryBean.setDataSource(datasource());
        factoryBean.setPackagesToScan(new String []{"com.spring.Model"});
        factoryBean.setHibernateProperties(hibernateProperties());
        return  factoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties=new Properties();
        properties.setProperty("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
    return  properties;
    }

    @Bean
    public DataSource datasource() {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        Locale.setDefault(Locale.ENGLISH);
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return  dataSource;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory s){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(s);
        return  transactionManager;
    }

}
