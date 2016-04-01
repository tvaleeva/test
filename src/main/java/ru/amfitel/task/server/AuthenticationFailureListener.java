package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import ru.amfitel.task.entity.LoginAttempt;
import ru.amfitel.task.entity.User;
import ru.amfitel.task.repository.LoginAttemptRepository;
import ru.amfitel.task.repository.UserRepository;

import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginAttemptRepository loginAttemptRepository;
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {

        String username = authenticationFailureBadCredentialsEvent.getAuthentication().getName();

        User user = userRepository.findByName(username);
        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setId_user(user);
        loginAttempt.setTime(new Date());
        loginAttemptRepository.save(loginAttempt);


    }
}
