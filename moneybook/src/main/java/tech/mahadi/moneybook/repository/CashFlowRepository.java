package tech.mahadi.moneybook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.mahadi.moneybook.entity.CashFlow;

import java.util.List;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {
    @Query("SELECT c FROM CashFlow c WHERE c.user.id = :userId")
    List<CashFlow> findAllCashFlowsByUserId(@Param("userId") Long userId);
}
