package com.epurashu.persistence.repository;

import java.util.Optional;

import com.epurashu.persistence.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>
{
    Optional<Users> findByUserName(String userName);
    Optional<Users> findByEmail(String email);
}
