package com.example.webclass.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginVM {
    private String login;
    private String password;
    private String randomCode;
    private String validateCode;
}