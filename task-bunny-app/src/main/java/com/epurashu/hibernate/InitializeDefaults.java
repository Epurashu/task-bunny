package com.epurashu.hibernate;

import org.springframework.stereotype.Component;

import com.epurashu.dto.AppUserRole;
import com.epurashu.persistence.entity.Users;
import com.epurashu.security.MyUserDetailsService;

@Component
public class InitializeDefaults
{
    private final MyUserDetailsService myUserDetailsService;

    public InitializeDefaults(MyUserDetailsService myUserDetailsService)
    {
        this.myUserDetailsService = myUserDetailsService;
        initialize();
    }

    private void initialize()
    {
        Users admin = new Users("admin", "admin@tb", "admiN123$", AppUserRole.ADMIN);
        myUserDetailsService.signUpUser(admin);
    }
}