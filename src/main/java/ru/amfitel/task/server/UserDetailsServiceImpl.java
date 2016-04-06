package ru.amfitel.task.server;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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



    private Integer maxExemptions;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("can't find user", new Throwable());
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);

        Integer countFailAttempt = loginAttempt.countFailAttempt(user.getId(), cal.getTime());

        Boolean blocked = countFailAttempt >= maxExemptions;

        user.setNonBlocked(blocked);
        //подсчитать кол-во попыток
        //обновить статус user
        userRepository.save(user);


        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getName(),
                        user.getPassword(), true, true, true, !blocked, Collections.emptySet());

        return userDetails;
    }

    public Integer getMaxExemptions() {
        return maxExemptions;
    }

    public void setMaxExemptions(Integer maxExemptions) {
        this.maxExemptions = maxExemptions;
    }
}
