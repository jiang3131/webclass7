package com.example.webclass;

import com.example.webclass.dto.User;
import com.example.webclass.repository.UserRepository;
import com.example.webclass.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebclassApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void addUser() {
        User user = new User();
        user.setLogin("123");
        String encode = passwordEncoder.encode("123");
        user.setPassword(encode);
        userRepository.save(user);
    }
}
