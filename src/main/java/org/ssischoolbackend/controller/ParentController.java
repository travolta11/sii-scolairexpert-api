package org.ssischoolbackend.controller;

import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.config.ApiResponse;
import org.ssischoolbackend.dto.ParentDto;
import org.ssischoolbackend.model.Parent;
import org.ssischoolbackend.service.ParentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parents")
@CrossOrigin(origins = "http://localhost:4200")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping
    public ApiResponse<ParentDto> getAllParents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Parent> parents = parentService.getAllParents(page, size);
        int totalParents = parentService.getTotalParents();
        int totalPages = (int) Math.ceil((double) totalParents / size);

        List<ParentDto> parentDtos = parents.stream().map(parent -> {
            ParentDto parentDto = new ParentDto();
            parentDto.setId(parent.getId());
            parentDto.setFirstName(parent.getFirstName());
            parentDto.setLastName(parent.getLastName());
            parentDto.setPhone(parent.getPhone());
            parentDto.setEmail(parent.getEmail());
            parentDto.setCin(parent.getCin());
            parentDto.setHasAssociatedStudents(parentService.hasAssociatedStudents(parent.getId()));
            return parentDto;
        }).collect(Collectors.toList());

        return new ApiResponse<>(parentDtos, totalPages);
    }

    @GetMapping("/id/{id}")
    public Parent getParentById(@PathVariable Long id) {
        return parentService.getParentById(id);
    }

    @GetMapping("/cin/{cin}")
    public Parent getParentByCin(@PathVariable String cin) {
        Parent parent = parentService.getParentByCin(cin);
        if (parent == null) {
            return null;
        }
        return parent;
    }

    @GetMapping("/check-cin-exists/{cin}")
    public boolean checkCinExists(@PathVariable String cin) {
        return parentService.isCinExists(cin);
    }

    @GetMapping("/check-email-exists/{email}")
    public boolean checkEmailExists(@PathVariable String email) {
        return parentService.isEmailExists(email);
    }

    @GetMapping("/check-phone-exists/{phone}")
    public boolean checkPhoneExists(@PathVariable String phone) {
        return parentService.isPhoneExists(phone);
    }

    @PostMapping
    public void saveParent(@RequestBody ParentDto parentDTO) {
        parentService.saveParent(parentDTO);
    }

    @PutMapping("/{id}")
    public void updateParent(@PathVariable Long id, @RequestBody ParentDto parentDTO) {
        parentService.updateParent(id, parentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
    }
}
