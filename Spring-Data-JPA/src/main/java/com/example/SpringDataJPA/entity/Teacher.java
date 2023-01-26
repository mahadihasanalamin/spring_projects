package com.example.SpringDataJPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_teacher")
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence")
    private Integer teacherId;
    private String firstName;
    private String lastName;
//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(name = "teacher_id")
//    private List<Course> courses;
}
