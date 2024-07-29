package org.ssischoolbackend.controller;

import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.config.ApiResponse;
import org.ssischoolbackend.dao.ParentDao;
import org.ssischoolbackend.dto.ParentDto;
import org.ssischoolbackend.model.Parent;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {
    private final ParentDao parentDAO;

    public ParentController(ParentDao parentDAO) {
        this.parentDAO = parentDAO;
    }

    @GetMapping
    public ApiResponse<Parent> getAllParents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        int offset = page * size;
        List<Parent> parents = parentDAO.getAllParents(offset, size);
        int totalParents = parentDAO.getTotalParents();
        int totalPages = (int) Math.ceil((double) totalParents / size);
        return new ApiResponse<>(parents, totalPages);
    }

    @GetMapping("/{id}")
    public Parent getParentById(@PathVariable Long id) {
        return parentDAO.getParentById(id);
    }

    @PostMapping
    public void saveParent(@RequestBody ParentDto parentDTO) {
        Parent parent = new Parent();
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setPhoneNumber(parentDTO.getPhoneNumber());
        parent.setEmail(parentDTO.getEmail());
        parentDAO.save(parent);
    }

    @PutMapping("/{id}")
    public void updateParent(@PathVariable Long id, @RequestBody ParentDto parentDTO) {
        Parent parent = new Parent();
        parent.setId(id);
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setPhoneNumber(parentDTO.getPhoneNumber());
        parent.setEmail(parentDTO.getEmail());
        parentDAO.update(parent);
    }

    @DeleteMapping("/{id}")
    public void deleteParent(@PathVariable Long id) {
        parentDAO.deleteById(id);
    }

}
