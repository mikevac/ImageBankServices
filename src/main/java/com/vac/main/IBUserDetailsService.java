package com.vac.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vac.main.data.dto.UserDto;
import com.vac.main.model.IBUserDetails;
import com.vac.main.services.UserService;

public class IBUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto ibUserDto = userService.findUser(username);
        if (ibUserDto == null) {
            throw new UsernameNotFoundException("");
        }
        return new IBUserDetails(ibUserDto);
    }

}
