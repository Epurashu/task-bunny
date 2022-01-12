package com.epurashu.controllers;

import static com.epurashu.controllers.Utils.COMPLEX_PASSWORD;
import static com.epurashu.controllers.Utils.EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.epurashu.SpringBootTestEnvironment;
import com.epurashu.adapters.UserAdapter;
import com.epurashu.dto.AppUserRole;
import com.epurashu.dto.UsersDTO;
import com.epurashu.persistence.entity.Users;

public class RegistrationControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRegistrationOfUser()
    {
        UsersDTO user = registrationController.register(UserAdapter.toDTO(new Users("bestadmin", EMAIL, COMPLEX_PASSWORD, AppUserRole.ADMIN)));
        assertNotNull(user);
        assertEquals(user.getUserName(), "bestadmin");
    }
}