/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 *
 * @author ASUS
 */
@Configuration
@ComponentScan(basePackages = "com.vmh")
@PropertySource({"classpath:database.properties"})
public class EmailConfig {

    @Autowired
    private Environment environment;

    /*
     * Application configuration
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Gmail SMTP configuration.
        mailSender.setHost(environment.getProperty("email.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("email.port")));

        /*
         *  gmail id and password
         */
        mailSender.setUsername(environment.getProperty("email.host.user"));
        mailSender.setPassword(environment.getProperty("email.host.password"));

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        javaMailProperties.put("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        javaMailProperties.put("mail.debug", environment.getProperty("mail.debug"));
//        javaMailProperties.put("mail.mime.charset", environment.getProperty("mail.mime.charset"));
        javaMailProperties.put("mail.smtp.allow8bitmime", environment.getProperty("mail.smtp.allow8bitmime"));
        javaMailProperties.put("mail.smtps.allow8bitmime", environment.getProperty("mail.smtps.allow8bitmime"));

        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration()
    {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setDefaultEncoding("UTF-8");
        bean.setTemplateLoaderPaths("classpath:/mailtemplate/");
        return bean;
    }
}
