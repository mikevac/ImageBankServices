package com.vac.main.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vac.main.model.IBUser;

public abstract class IBService {

    public IBUser getUser() {
        return (IBUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
