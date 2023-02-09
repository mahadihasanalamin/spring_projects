package com.example.springsecurityclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String repeatPassword;
}
