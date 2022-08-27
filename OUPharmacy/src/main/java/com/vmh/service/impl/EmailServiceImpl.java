/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.constant.EmailConstant;
import com.vmh.service.EmailService;
import freemarker.template.Configuration;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author ASUS
 */
@Service("emailService")
@PropertySource("classpath:database.properties")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfiguration;

    @Autowired
    private Environment environment;

    @Override
    public boolean sendMail(String subject, String[] to, Map<String,Object> model) {
        MimeMessagePreparator preparator = getMessagePreparator(subject, to, model);

        try {
            javaMailSender.send(preparator);
            return true;
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    private MimeMessagePreparator getMessagePreparator(final String subject, 
            final String[] to,final Map<String,Object> model) {

        return new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject(subject);
                helper.setFrom(Objects.requireNonNull(environment.getProperty("default.from.email")));
                helper.setTo(to);
                String text = geFreeMarkerTemplateContent(model);
                /*
                 * use the true flag to indicate you need a multipart message
                 */
//                helper.setText(text, true);
                helper.getMimeMessage().setContent(text, "text/html;charset=utf-8");

                /*
                 * Additionally, let's add a resource as an attachment as well.
                 */
                // helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
            }
        };
    }

    public String geFreeMarkerTemplateContent(Map<String,Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("email-sender.ftl"), model));

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
