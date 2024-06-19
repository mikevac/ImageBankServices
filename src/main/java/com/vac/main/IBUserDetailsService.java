package com.vac.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vac.main.data.dto.UserDto;
import com.vac.main.model.IBUserDetails;
import com.vac.main.services.UserService;

@Configuration
public class IBUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto ibUser = userService.findUser(username);
        if (ibUser == null) {
            throw new UsernameNotFoundException("");
        }
        return new IBUserDetails(ibUser);
    }

    @Bean
    public IBUserDetailsService ibUserDetailsService() {
        return new IBUserDetailsService();
    }

}
