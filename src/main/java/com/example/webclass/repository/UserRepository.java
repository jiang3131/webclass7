package com.example.webclass.repository;

import com.example.webclass.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserNameAndPassword(String username, String password);

    User findUserByLoginAndPassword(String login, String password);
}