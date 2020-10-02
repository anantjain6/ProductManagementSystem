package me.anant.PMS.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * This class is responsible to setup Email Configurations.
 */
@Configuration
public class EmailConfig {

    /**
     * This method is responsible set following JavaMailSender configurations : <br>
     *     <ul>
     *         <li>host</li>
     *         <li>port</li>
     *         <li>username</li>
     *         <li>password</li>
     *     </ul>
     * <br>
     *     Also Following JavaMailproperties are set up :
     *     <ul>
     *         <li>mail.transport.protocol</li>
     *         <li>mail.smtp.auth</li>
     *         <li>mail.smtp.starttls.enable</li>
     *         <li>mail.debug</li>
     *     </ul>
     * @return JavaMailSender
     */
    @Bean
    public JavaMailSender getJavaMailSender() 
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
          
        mailSender.setUsername("53b095fe63e8b6");
        mailSender.setPassword("c87dc71a300727");
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
          
        return mailSender;
    }
}
