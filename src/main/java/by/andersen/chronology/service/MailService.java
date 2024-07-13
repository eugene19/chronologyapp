package by.andersen.chronology.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Service for sending registration confirmation emails.
 */
public interface MailService {

    /**
     * Sends a registration confirmation email to the specified user.
     *
     * @param user  the user to send the email to
     */
    void sendRegistrationConfirmationMail(UserDetails user);
}
