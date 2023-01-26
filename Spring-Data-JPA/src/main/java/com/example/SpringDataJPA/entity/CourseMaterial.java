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
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "course_id")
    private Course course;
}
