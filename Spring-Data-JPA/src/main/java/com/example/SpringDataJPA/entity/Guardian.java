package com.example.SpringDataJPA.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "guardian_name")),
        @AttributeOverride(name = "phone", column = @Column(name = "guardian_phone")),
        @AttributeOverride(name = "email", column = @Column(name = "guardian_email"))
})
public class Guardian {
    private String name;
    private String phone;
    private String email;
}
