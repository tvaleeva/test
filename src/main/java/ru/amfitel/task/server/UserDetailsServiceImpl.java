package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.amfitel.task.entity.User;
import ru.amfitel.task.repository.LoginAttemptRepository;
import ru.amfitel.task.repository.UserRepository;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Bublik on 31.03.2016.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginAttemptRepository loginAttempt;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        Integer countFailAttempt = loginAttempt.countFailAttempt(user.getId(), cal.getTime());
        Boolean status = true;
        if (countFailAttempt >= 4) {
            status = false;
        }
        user.setNonBlocked(status);
        //подсчитать кол-во попыток
        //обновить статус user
        userRepository.save(user);


        //передаем статус
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getName(),
                        user.getPassword(), true, true, true, status, Collections.emptySet());

        return userDetails;
    }
}
