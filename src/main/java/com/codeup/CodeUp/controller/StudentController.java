package com.codeup.CodeUp.controller;

import com.codeup.CodeUp.domain.StudentEntity;
import com.codeup.CodeUp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create a new student
    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student) {
        StudentEntity savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        Optional<StudentEntity> studentOpt = studentRepository.findById(id);
        return studentOpt
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a student
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long id, @RequestBody StudentEntity student) {
        Optional<StudentEntity> existingStudentOpt = studentRepository.findById(id);

        if (existingStudentOpt.isPresent()) {
            StudentEntity existingStudent = existingStudentOpt.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setStudentEmail(student.getStudentEmail());
            existingStudent.setStudentPassword(student.getStudentPassword());
            studentRepository.save(existingStudent);
            return new ResponseEntity<>(existingStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
