package by.andersen.chronology.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.andersen.chronology.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendRegistrationConfirmationMail(UserDetails user) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getUsername());
        simpleMailMessage.setSubject("Registration confirmation");
        simpleMailMessage.setText("Hello! Your registration was successful!");
        javaMailSender.send(simpleMailMessage);

        log.debug("Registration confirmation email sent to: {}", user.getUsername());
    }
}
