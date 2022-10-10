package me.anant.PMS.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void testSend() {

        Mockito.doNothing().when(javaMailSender).send(Mockito.any(MimeMessagePreparator.class));

        emailService.send("a@a.com", "subject-1", "a whole body");

        Mockito.verify(javaMailSender, Mockito.times(1)).send(Mockito.any(MimeMessagePreparator.class));
    }
}
