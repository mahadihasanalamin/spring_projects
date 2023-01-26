package com.example.SpringDataJPA.repository;

import com.example.SpringDataJPA.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameIgnoreCase(String firstName);
    List<Student> findByFirstNameContaining(String name);
//    List<Student> findByLastNameNotNull(String lastName);
    List<Student> findByGuardianName(String guardianName);
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId = :emailId")
    String getFirstNameByEmailAddress(@Param("emailId") String emailId);

    @Modifying
    @Transactional //It should be used in service layer;
    @Query("update Student s set s.firstName = :firstName where s.emailId = :emailId")
    Integer updateStudentNameByEmailId(@Param("emailId") String emailId, @Param("firstName") String firstName);
}
