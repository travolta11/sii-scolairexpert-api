package org.ssischoolbackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.ClassDao;
import org.ssischoolbackend.dto.ClassDto;
import org.ssischoolbackend.model.Class;

@Service
public class ClassService {

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClassDto> getAllClasses() {
        List<Class> classes = classDao.getAllClasses();
        return classes.stream()
                .map(clazz -> modelMapper.map(clazz, ClassDto.class))
                .collect(Collectors.toList());
    }

    public ClassDto getClassById(Long id) {
        Class clazz = classDao.getClassById(id);
        return modelMapper.map(clazz, ClassDto.class);
    }

    public long createClass(ClassDto classDto) {
        Class clazz = modelMapper.map(classDto, Class.class);
        return classDao.createClass(clazz);
    }

    public int updateClass(ClassDto classDto) {
        Class clazz = modelMapper.map(classDto, Class.class);
        return classDao.updateClass(clazz);
    }

    public int deleteClass(Long id) {
        return classDao.deleteClass(id);
    }
}
