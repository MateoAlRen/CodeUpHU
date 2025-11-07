package com.codeup.CodeUp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "Subjects"
)
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacherId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentId;
    @Column(
            name = "subject_grade",
            nullable = false,
            precision = 5,
            scale = 2
    )
    private BigDecimal subjectGrade;
}
