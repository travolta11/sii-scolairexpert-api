package org.ssischoolbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.UserDao;
import org.ssischoolbackend.dto.UserDto;
import org.ssischoolbackend.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private final ModelMapper modelMapper = new ModelMapper();

    public Long createUser(UserDto userDto) {
        return userDao.createUser(modelMapper.map(userDto, User.class));
    }

    public Optional<UserDto> getUserById(Long id) {
        return userDao.getUserById(id).map(user -> modelMapper.map(user, UserDto.class));
    }

    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public void updateUser(Long id, UserDto userDto) {
        userDao.updateUser(id, modelMapper.map(userDto, User.class));
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
