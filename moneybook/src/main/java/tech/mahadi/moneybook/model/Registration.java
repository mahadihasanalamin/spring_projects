package tech.mahadi.moneybook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    private String fullName;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;
}
