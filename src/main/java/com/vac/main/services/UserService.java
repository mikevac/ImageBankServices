package com.vac.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vac.main.data.dto.UserDto;
import com.vac.main.exceptions.UserNotFoundException;
import com.vac.main.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserDto findUser(String user) {
        if (user != null) {
            return userRepo.findUser(user);
        }
        throw new UserNotFoundException();
    }

}
