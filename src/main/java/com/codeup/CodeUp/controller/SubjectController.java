package com.codeup.CodeUp.controller;

import com.codeup.CodeUp.domain.SubjectEntity;
import com.codeup.CodeUp.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    // Create a new subject
    @PostMapping
    public ResponseEntity<SubjectEntity> createSubject(@RequestBody SubjectEntity subject) {
        SubjectEntity savedSubject = subjectRepository.save(subject);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    // Get all subjects
    @GetMapping
    public ResponseEntity<List<SubjectEntity>> getAllSubjects() {
        List<SubjectEntity> subjects = subjectRepository.findAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    // Get a subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubjectEntity> getSubjectById(@PathVariable Long id) {
        Optional<SubjectEntity> subjectOpt = subjectRepository.findById(id);
        return subjectOpt
                .map(subject -> new ResponseEntity<>(subject, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a subject
    @PutMapping("/{id}")
    public ResponseEntity<SubjectEntity> updateSubject(@PathVariable Long id, @RequestBody SubjectEntity subject) {
        Optional<SubjectEntity> existingSubjectOpt = subjectRepository.findById(id);

        if (existingSubjectOpt.isPresent()) {
            SubjectEntity existingSubject = existingSubjectOpt.get();
            existingSubject.setTeacherId(subject.getTeacherId());
            existingSubject.setStudentId(subject.getStudentId());
            existingSubject.setSubjectGrade(subject.getSubjectGrade());
            subjectRepository.save(existingSubject);
            return new ResponseEntity<>(existingSubject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a subject
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
