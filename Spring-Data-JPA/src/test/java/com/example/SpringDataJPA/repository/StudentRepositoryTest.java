package com.example.SpringDataJPA.repository;

import com.example.SpringDataJPA.entity.Guardian;
import com.example.SpringDataJPA.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    public void saveStudentInfo()
    {
        Guardian guardian = Guardian.builder()
                .name("James Bond")
                .email("james@gmail.com")
                .phone("9876490245")
                .build();
        Student student = Student.builder()
                .firstName("John")
                .lastName("Shelby")
                .emailId("john@gmail.com")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }
    @Test
    public void fetchStudentByFirstNameContaining()
    {
        List<Student> students = studentRepository.findByFirstNameContaining("H");
        students.forEach(student -> {
            System.out.println(student.getFirstName());
        });
    }

    @Test
    public void getStudentByEmailAddress()
    {
        Student student = studentRepository.getStudentByEmailAddress("mahadi.aalamin@gmail.com");
        System.out.println("student: "+student);
    }

    @Test
    public void getStudentFirstNameByEmailAddress()
    {
        String student = studentRepository.getFirstNameByEmailAddress("mahadi.aalamin@gmail.com");
        System.out.println("student: "+student);
    }

    @Test
    public void updateStudentNameByEmailAddress()
    {
        studentRepository.updateStudentNameByEmailId("mahadi.aalamin@gmail.com", "Mahadi");
    }
}