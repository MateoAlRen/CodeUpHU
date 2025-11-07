package com.codeup.CodeUp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Teachers",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "teacher_email"
        )
)
public class TeacherEntity {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    @Column(
            nullable = false,
            name = "first_name"
    )
    private String firstName;
    @Column(
            nullable = false,
            name = "last_name"
    )
    private String lastName;
    @Column(
            nullable = false,
            name = "teacher_email"
    )
    private String teacherEmail;
    @Column(
            nullable = false,
            name = "teacher_password"
    )
    private String teacherPassword;

}
