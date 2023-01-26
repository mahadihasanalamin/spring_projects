package com.example.SpringDataJPA.repository;

import com.example.SpringDataJPA.entity.Course;
import com.example.SpringDataJPA.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository repository;
    @Test
    public void saveCourseMaterialWithCourse()
    {
        Course course = Course.builder().title("Algorithm").credit(3).build();
        CourseMaterial courseMaterial = CourseMaterial.builder().course(course).url("www.algorithm.com").build();
        repository.save(courseMaterial);
    }

    @Test
    public void getCourseMaterial(){
        List<CourseMaterial> courseMaterial = repository.findAll();
        System.out.println(courseMaterial);
    }
}