package by.andersen.chronology.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
public class MailServiceImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailServiceImpl mailService;

    @Test
    void sendRegistrationConfirmationMailTest() {
        UserDetails user = new User("testUser", "password", Collections.singleton(new SimpleGrantedAuthority("USER")));
        
        mailService.sendRegistrationConfirmationMail(user);
        
        ArgumentCaptor<SimpleMailMessage> argument = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender, times(1)).send(argument.capture());
        SimpleMailMessage sentMessage = argument.getValue();
        Assertions.assertEquals("Registration confirmation", sentMessage.getSubject());
        Assertions.assertEquals("Hello! Your registration was successful!", sentMessage.getText());
    }
}