package com.example.webclass.service;

import com.example.webclass.dto.User;
import com.example.webclass.utils.Jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {


    Jdbc myjdbc=new Jdbc();
    //拿到令牌
    public String getToken(String login){
        return  "thisisatoken."+login+".tokenend";
    }
    public User getUser(String token){
        try {
            String login = token.split("\\.")[1];
            return new User(login);
        }catch (Exception e){
            e.printStackTrace();
        }
          return null;
    }
}
