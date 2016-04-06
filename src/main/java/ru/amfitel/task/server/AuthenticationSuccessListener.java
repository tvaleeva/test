package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.amfitel.task.entity.LoginAttempt;
import ru.amfitel.task.entity.User;
import ru.amfitel.task.repository.LoginAttemptRepository;
import ru.amfitel.task.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginAttemptRepository loginAttempt;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        String username = authenticationSuccessEvent.getAuthentication().getName();

        User user = userRepository.findByName(username);
       // loginAttempt.deleteUserAttempt(user);
        user.getLoginAttempts().clear();
        userRepository.saveAndFlush(user);


    }

    /*@Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {

        String username = authenticationFailureBadCredentialsEvent.getAuthentication().getName();

        User user = userRepository.findByName(username);
        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setId_user(user);
        loginAttempt.setTime(new Date());
        loginAttemptRepository.save(loginAttempt);


    }*/
}
