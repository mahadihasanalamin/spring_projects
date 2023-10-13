package tech.mahadi.moneybook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.mahadi.moneybook.enumeration.Role;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_user")
public class User implements UserDetails {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CashFlow> cashFlows;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Book> books;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Token token;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Category> categories;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() { return password; }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
