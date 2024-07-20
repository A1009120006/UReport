package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.bstek.ureport.UReportPropertyPlaceholderConfigurer;
import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
这个必要有否则报表设计页面报错
@ImportResource("classpath:ureport-console-context.xml")
*/
@Configuration
@Slf4j
@ImportResource("classpath:ureport-console-context.xml")
public class ReportConfig {

    private DataSource dataSource;

    //添加 report 的servlet
    @Bean
    public ServletRegistrationBean<Servlet> ureport2Servlet() {
        return new ServletRegistrationBean<>(new UReportServlet(), "/ureport/*");
    }
    //这一步省略了创建配置文件
    @Bean
    public UReportPropertyPlaceholderConfigurer UReportPropertyPlaceholderConfigurer(){
        UReportPropertyPlaceholderConfigurer propertyConfigurer=new UReportPropertyPlaceholderConfigurer();
        propertyConfigurer.setIgnoreUnresolvablePlaceholders(true);
        ClassPathResource pathResource=new ClassPathResource("context.properties");
        propertyConfigurer.setLocation(pathResource);
        return propertyConfigurer;
    }

}