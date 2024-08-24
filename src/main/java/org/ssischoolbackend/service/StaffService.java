package org.ssischoolbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.StaffDao;
import org.ssischoolbackend.dto.EspaceDto;
import org.ssischoolbackend.dto.StaffDto;
import org.ssischoolbackend.model.Espace;
import org.ssischoolbackend.model.Staff;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private StaffDao staffDao;

    private final ModelMapper modelMapper = new ModelMapper();
    public Long createStaffMember(StaffDto staffdto) {
        return this.staffDao.createStaffMember(this.getStaff(staffdto));
    }


    public Optional<StaffDto> getStaffByID(Long id) {
       Optional<Staff> staffOptional=staffDao.getStaffById(id);
       return staffOptional.map(this::getStaffDto);
    }

    public List<StaffDto> getAllStaff() {
        return this.staffDao.getAllStaffMembers().stream().map(this::getStaffDto).collect(Collectors.toList());
    }
    public boolean deleteStaffMember(Long id){
        return this.staffDao.deleteStaffMember(id);
    }
    public boolean updateStaffMember(StaffDto staffDto) {
        return this.staffDao.updateStaffMember(this.getStaff(staffDto));
    }


    private Staff getStaff(StaffDto staffDto) {
        return modelMapper.map(staffDto, Staff.class);
    }

    private StaffDto getStaffDto(Staff staff) {
        return modelMapper.map(staff, StaffDto.class);
    }

    public Map<String, Integer> getStaffCountByDepartment() {
        return staffDao.getStaffCountByDepartment();
    }

}
