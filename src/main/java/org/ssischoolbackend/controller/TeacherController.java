package org.ssischoolbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.service.TeacherService;
import org.ssischoolbackend.dto.TeacherDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
// @CrossOrigin(origins = "http://localhost:4200") 
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @PostMapping("")
    public ResponseEntity<Long> createNewTeacher(@RequestBody TeacherDto teacherDto) {
        Long teacherId = teacherService.createTeacher(teacherDto);
        return ResponseEntity.ok(teacherId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable("id") Long id){
        Optional<TeacherDto> teacherDto = teacherService.getTeacherByID(id);
        return teacherDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto) {
        teacherDto.setId(id);
        int updated = teacherService.updateTeacher(teacherDto);
        if (updated == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        int deleted = teacherService.deleteTeacher(id);
        if (deleted == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
