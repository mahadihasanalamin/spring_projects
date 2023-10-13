package tech.mahadi.moneybook.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.mahadi.moneybook.entity.Token;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    Token findByUserId(Long user_id);
    boolean existsByUserId(Long user_id);
    boolean existsByToken(String token);
    @Modifying
    @Transactional
    @Query("DELETE FROM Token t WHERE t.user.id = :user_id")
    void deleteByUserId(@Param("user_id") Long user_id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Token t WHERE t.token = :token")
    void deleteByToken(@Param("token") String token);
}