package org.ssischoolbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.dto.EspaceDto;
import org.ssischoolbackend.dto.StaffDto;
import org.ssischoolbackend.model.Staff;
import org.ssischoolbackend.service.StaffService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "http://localhost:4200")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/all")
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        return ResponseEntity.ok(this.staffService.getAllStaff());
    }

    @PostMapping("")
    public long createNewEspace(@RequestBody StaffDto staffdto) {
        return this.staffService.createStaffMember(staffdto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffMemberById(@PathVariable("id") Long id){
        Optional<StaffDto> staffDto= this.staffService.getStaffByID(id);
        if(staffDto.isPresent()){
            return new ResponseEntity<>(staffDto.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStaffMember(@PathVariable Long id) {
        boolean isDeleted = staffService.deleteStaffMember(id);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateStaffMember(@PathVariable Long id, @RequestBody StaffDto staffDto) {
        boolean isUpdated = staffService.updateStaffMember(staffDto);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count/by-department")
    public ResponseEntity<Map<String, Integer>> getStaffCountByDepartment() {
        return ResponseEntity.ok(staffService.getStaffCountByDepartment());
    }


}
