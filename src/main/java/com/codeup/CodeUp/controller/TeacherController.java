package com.codeup.CodeUp.controller;

import com.codeup.CodeUp.domain.TeacherEntity;
import com.codeup.CodeUp.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    // Create a new teacher
    @PostMapping
    public ResponseEntity<TeacherEntity> createTeacher(@RequestBody TeacherEntity teacher) {
        TeacherEntity savedTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    // Get all teachers
    @GetMapping
    public ResponseEntity<List<TeacherEntity>> getAllTeachers() {
        List<TeacherEntity> teachers = teacherRepository.findAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Get a teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeacherEntity> getTeacherById(@PathVariable Long id) {
        Optional<TeacherEntity> teacherOpt = teacherRepository.findById(id);
        return teacherOpt
                .map(teacher -> new ResponseEntity<>(teacher, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a teacher
    @PutMapping("/{id}")
    public ResponseEntity<TeacherEntity> updateTeacher(@PathVariable Long id, @RequestBody TeacherEntity teacher) {
        Optional<TeacherEntity> existingTeacherOpt = teacherRepository.findById(id);

        if (existingTeacherOpt.isPresent()) {
            TeacherEntity existingTeacher = existingTeacherOpt.get();
            existingTeacher.setFirstName(teacher.getFirstName());
            existingTeacher.setLastName(teacher.getLastName());
            existingTeacher.setTeacherEmail(teacher.getTeacherEmail());
            existingTeacher.setTeacherPassword(teacher.getTeacherPassword());
            teacherRepository.save(existingTeacher);
            return new ResponseEntity<>(existingTeacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
