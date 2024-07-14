package com.vac.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.exceptions.UserNotFoundException;
import com.vac.main.repositories.RoleRepository;
import com.vac.main.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Transactional(readOnly = true)
    public UserDto findUser(String user) {
        if (user != null) {
            return userRepo.findUser(user);
        }
        throw new UserNotFoundException();
    }

    @Transactional
    public void createUser(UserDto userDto) {
        RoleDto roleDto = roleRepo.find("USER");
        userRepo.createUser(userDto, roleDto);
    }

}
