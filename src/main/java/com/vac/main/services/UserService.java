package com.vac.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vac.main.constants.ServiceStatus;
import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.exceptions.RoleNotFoundException;
import com.vac.main.exceptions.UserNotFoundException;
import com.vac.main.repositories.RoleRepository;
import com.vac.main.repositories.UserRepository;
import com.vac.main.services.response.GenericServiceResponse;

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
    public GenericServiceResponse createUser(UserDto userDto) {
        RoleDto roleDto = roleRepo.find("USER");
        if (roleDto != null) {
            userRepo.createUser(userDto, roleDto);
            return new GenericServiceResponse(ServiceStatus.SUCCESS, "User has been created");
        }
        throw new RoleNotFoundException();
    }

}
