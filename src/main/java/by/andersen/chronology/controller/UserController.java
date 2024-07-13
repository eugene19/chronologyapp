package by.andersen.chronology.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import by.andersen.chronology.config.security.HasAdminRole;
import by.andersen.chronology.config.security.UserRole;
import by.andersen.chronology.dto.UserDto;
import by.andersen.chronology.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    private final MailService mailService;

    @HasAdminRole
    @MutationMapping
    public String createUser(@Validated @Argument UserDto user) {
        log.debug("Create user: {}", user);
        UserDetails userDetails = User.withUsername(user.username())
                        .password(passwordEncoder.encode(user.password()))
                        .roles(UserRole.USER.name())
                        .build();

        userDetailsManager.createUser(userDetails);

        mailService.sendRegistrationConfirmationMail(userDetails);

        return "User has been created successfully";
    }

}
