package com.example.webclass.apis;


import com.example.webclass.dto.LoginVM;
import com.example.webclass.dto.User;
import com.example.webclass.repository.UserRepository;
import com.example.webclass.service.UserTokenService;
import com.example.webclass.service.ValidateCodeService;
import com.example.webclass.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping("/api/login")
public class LoginApi {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ValidateCodeService validateCodeService;
    @Autowired
    UserTokenService userTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    protected Object login(@RequestBody LoginVM loginVM, HttpServletResponse response) throws ServletException, IOException {
        String login = loginVM.getLogin();
        String password = loginVM.getPassword();
        String randomCode = loginVM.getRandomCode();
        String validateCode = loginVM.getValidateCode();
        if (validateCodeService.validate(randomCode, validateCode)) {
            String encode = passwordEncoder.encode(password);
            User user = userRepository.findUserByLoginAndPassword(login, encode);
            if (user != null) {
                String token = userTokenService.getToken(login);
                HashMap<String, String> result = new HashMap<>();
                result.put("token", token);
                return ResponseEntity.ok(result);
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("error", "用户名密码不存在");
                return ResponseEntity.status(401).body(error);
            }
        } else {
            HashMap<String, String> result = new HashMap<>();
            result.put("error", "验证码错误");
            return ResponseEntity.status(400).body(result);
        }
    }
}
