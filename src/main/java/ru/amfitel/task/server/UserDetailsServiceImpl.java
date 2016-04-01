package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.amfitel.task.entity.User;
import ru.amfitel.task.repository.BuildRepository;
import ru.amfitel.task.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Set;

/**
 * Created by Bublik on 31.03.2016.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Set roles =  Collections.singleton(new SimpleGrantedAuthority("ROLE"));
        User user = userRepository.findByName(username);
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getName(),
                        user.getPassword(), roles );
        return userDetails;
    }
}
