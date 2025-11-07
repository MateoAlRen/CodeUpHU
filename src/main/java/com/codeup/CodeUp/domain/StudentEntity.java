package com.codeup.CodeUp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "students",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "student_email"
        )
)
public class StudentEntity {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "student_email",
            nullable = false
    )
    private String studentEmail;
    @Column(
            name = "student_password",
            nullable = false
    )
    private String studentPassword;

}
