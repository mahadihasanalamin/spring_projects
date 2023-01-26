package com.example.SpringDataJPA.repository;

import com.example.SpringDataJPA.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getAllCourse(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses: "+courses);
    }

    @Test
    public void saveCourses(){
        Course course = Course.builder()
                .title("Introduction to programming")
                .credit(3)
                .build();
        courseRepository.save(course);
    }

}