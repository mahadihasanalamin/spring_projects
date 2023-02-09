package com.example.springsecurityclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordModel {
    private String email;
    private String oldPassword;
    private String newPassword;
}
