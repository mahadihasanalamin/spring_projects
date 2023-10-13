package tech.mahadi.moneybook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long token_id;
    private String token;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
