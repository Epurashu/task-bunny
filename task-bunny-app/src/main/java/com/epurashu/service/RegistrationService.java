package com.epurashu.service;

import org.springframework.stereotype.Service;

import com.epurashu.dto.AppUserRole;
import com.epurashu.dto.UsersDTO;
import com.epurashu.persistence.entity.Users;
import com.epurashu.security.MyUserDetailsService;

@Service
public class RegistrationService
{
    private final MyUserDetailsService myUserDetailsService;

    public RegistrationService(MyUserDetailsService myUserDetailsService)
    {
        this.myUserDetailsService = myUserDetailsService;
    }

    public UsersDTO register(UsersDTO request)
    {
        return myUserDetailsService.signUpUser(
            new Users(
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.ADMIN));
    }
}