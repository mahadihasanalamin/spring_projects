package com.example.SpringDataJPA.repository;

import com.example.SpringDataJPA.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentEntityRepository {
    @Autowired
    private EntityManager entityManager;

    public void save(Student student){
        entityManager.persist(student);
    }
}
