package com.example.SpringDataJPA.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_course_material")
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @Column(name = "course_material_id")
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence")
    private Integer courseMaterialId;
    @Column(name = "course_material_url")
    private String url;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, //It will fetch only course material
                                    //FetchType.EAGER will fetch the course as well
            optional = false //whenever we want to add course material we also need to add the course
    )
    @JoinColumn(name = "course_id")
    private Course course;
}
