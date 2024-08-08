package org.ssischoolbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.dto.SubjectDto;
import org.ssischoolbackend.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectDto> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectDto getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public long createSubject(@RequestBody SubjectDto subjectDto) {
        return subjectService.createSubject(subjectDto);
    }

    @PutMapping("/{id}")
    public int updateSubject(@PathVariable Long id, @RequestBody SubjectDto subjectDto) {
        subjectDto.setId(id);
        return subjectService.updateSubject(subjectDto);
    }

    @DeleteMapping("/{id}")
    public int deleteSubject(@PathVariable Long id) {
        return subjectService.deleteSubject(id);
    }
    @GetMapping("/SubjectsByClass/{id}")
    public List<SubjectDto> getSubjectByClass(@PathVariable Long id) {
    	return subjectService.getSubjectsByClassId(id);
    }
}
