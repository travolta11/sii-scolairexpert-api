package org.ssischoolbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.SubjectDao;
import org.ssischoolbackend.dto.SubjectDto;
import org.ssischoolbackend.model.Subject;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private ModelMapper modelMapper;

    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectDao.getAllSubjects();
        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    public SubjectDto getSubjectById(Long id) {
        Subject subject = subjectDao.getSubjectById(id);
        return modelMapper.map(subject, SubjectDto.class);
    }

    public long createSubject(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        return subjectDao.createSubject(subject);
    }

    public int updateSubject(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        return subjectDao.updateSubject(subject);
    }

    public int deleteSubject(Long id) {
        return subjectDao.deleteSubject(id);
    }
    
    public List<SubjectDto> getSubjectsByClassId(Long classId) {
        List<Subject> subjects = subjectDao.getSubjectsByClassId(classId);
        return subjects.stream()
                       .map(subject -> modelMapper.map(subject, SubjectDto.class))
                       .collect(Collectors.toList());
    }
}
