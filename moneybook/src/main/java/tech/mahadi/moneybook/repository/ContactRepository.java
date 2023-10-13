package tech.mahadi.moneybook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.mahadi.moneybook.entity.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c from Contact c WHERE c.user.id =:userId")
    List<Contact> findAllContactByUserId(@Param("userId") Long userId);
}
