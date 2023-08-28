package com.vac.main.services;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.exceptions.InvalidUserIdException;
import com.vac.main.exceptions.UserNotFoundException;
import com.vac.main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<RoleDto> getRoles(Integer userId) {
        if (userId != null) {
            return userRepo.fetchRoles(userId);
        }
        throw new InvalidUserIdException();
    }
}
