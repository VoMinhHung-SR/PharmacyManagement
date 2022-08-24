/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.config;

import com.vmh.converter.DateConverter;
import com.vmh.formatter.CategoryFormatter;
import com.vmh.formatter.MedicineFormatter;
import com.vmh.service.MedicineService;
import com.vmh.service.UserService;
import com.vmh.validator.MedicineNameUniqueValidator;
import com.vmh.validator.MedicineUnitValidator;
import com.vmh.validator.UserEmailValidator;
import com.vmh.validator.UsernameValidator;
import com.vmh.validator.WebAppValidator;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author ASUS
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.vmh.controller", "com.vmh.repository",
    "com.vmh.service", "com.vmh.api", "com.vmh.validator", "com.vmh.handler"})
public class WebApplicationContextConfig implements WebMvcConfigurer {

    @Autowired
    private MedicineService medicineService;
    
    @Autowired
    private UserService userService;
    
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        
        registry.addResourceHandler("/admin/**").addResourceLocations("/resources/admin/");

    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");

        return resolver;

    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;

    }
    //====Validator====
    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    
    @Bean
    public WebAppValidator medicineValidator(){
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new MedicineNameUniqueValidator(medicineService));
        
        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setSpringValidators(springValidators);
        return webAppValidator;
    }
    
    @Bean
    public WebAppValidator medicineUnitValidator(){
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new MedicineUnitValidator());
        
        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setSpringValidators(springValidators);
        return webAppValidator;
    }
       
    @Bean
    public WebAppValidator userValidator(){
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UsernameValidator(userService));
        springValidators.add(new UserEmailValidator(userService));
        
        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setSpringValidators(springValidators);
        return webAppValidator;
    }
    
    @Override
    public Validator getValidator() {
        return validator();
    }
    //====End Validator====
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter("yyyy-mm-dd"));
        registry.addFormatter(new CategoryFormatter());
        registry.addFormatter(new MedicineFormatter());
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
}
