package com.epurashu.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.epurashu.dto.UsersDTO;
import com.epurashu.adapters.UserAdapter;
import com.epurashu.persistence.entity.Users;
import com.epurashu.persistence.repository.UsersRepository;
import com.epurashu.security.MyUserDetailsService;

@Service
public class UserService
{
    private final UsersRepository usersRepository;
    private final MyUserDetailsService myUserDetailsService;

    public UserService(UsersRepository usersRepository, MyUserDetailsService myUserDetailsService)
    {
        this.usersRepository = usersRepository;
        this.myUserDetailsService = myUserDetailsService;
    }

    public List<UsersDTO> findAll()
    {
        return UserAdapter.toDTOList(usersRepository.findAll());
    }

    public UsersDTO findById(Integer id)
    {
        Users users = getUserById(id);
        return UserAdapter.toDTO(users);
    }

    public UsersDTO createUser(UsersDTO usersDTO)
    {
        Users users = UserAdapter.toEntity(usersDTO);
        Users savedUser = usersRepository.save(users);
        return UserAdapter.toDTO(savedUser);
    }

    public UsersDTO updateUser(Integer id, UsersDTO userDTO)
    {
        Users theUser = getUserById(id);
        userDTO.setPassword(theUser.getPassword());
        if (!theUser.getId().equals(userDTO.getId()))
        {
            throw new RuntimeException("Id of entity not the same with path id");
        }
        return UserAdapter.toDTO(usersRepository.save(UserAdapter.toEntity(userDTO)));
    }

    public void deactivateUser(Integer id)
    {
        Users users = getUserById(id);
        users.setActive(false);
        usersRepository.save(users);
    }

    private Users getUserById(Integer id)
    {
        Optional<Users> optional = usersRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public String updatePassword(Integer id, String password)
    {
        Users users = getUserById(id);
        users.setPassword(password);
        return myUserDetailsService.changePassword(users);
    }
}