package tech.mahadi.moneybook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "tbl_book")
public class Book {
    @Id
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(columnDefinition = "integer default 0")
    private Integer cashIn = 0;
    @Column(columnDefinition = "integer default 0")
    private Integer cashOut = 0;
    @Column(columnDefinition = "integer default 0")
    private Integer balance = 0;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<CashFlow> cashFlows;
}
