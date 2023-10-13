package tech.mahadi.moneybook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<CashFlow> cashFlows;
}
