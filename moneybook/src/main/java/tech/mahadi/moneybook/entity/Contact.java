package tech.mahadi.moneybook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.mahadi.moneybook.enumeration.ContactType;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id", nullable = false)
    private Long id;
    private String name;
    private String phone;
    @Enumerated(EnumType.STRING)
    private ContactType contactType;
    @JsonIgnore
    @OneToMany(mappedBy = "contact")
    private List<CashFlow> cashFlows;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
