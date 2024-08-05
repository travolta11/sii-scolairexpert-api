package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.ParentDao;
import org.ssischoolbackend.dto.ParentDto;
import org.ssischoolbackend.model.Parent;

import java.util.List;

@Service
public class ParentService {
    private final ParentDao parentDAO;

    public ParentService(ParentDao parentDAO) {
        this.parentDAO = parentDAO;
    }

    public List<Parent> getAllParents(int page, int size) {
        int offset = page * size;
        return parentDAO.getAllParents(offset, size);
    }

    public Parent getParentById(Long id) {
        return parentDAO.getParentById(id).orElse(null);
    }

    public void saveParent(ParentDto parentDTO) {
        Parent parent = new Parent();
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setPhoneNumber(parentDTO.getPhoneNumber());
        parent.setEmail(parentDTO.getEmail());
        parentDAO.createNewParent(parent);
    }

    public void updateParent(Long id, ParentDto parentDTO) {
        Parent parent = new Parent();
        parent.setId(id);
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setPhoneNumber(parentDTO.getPhoneNumber());
        parent.setEmail(parentDTO.getEmail());
        parentDAO.updateParent(parent);
    }

    public void deleteParent(Long id) {
        parentDAO.deleteParentById(id);
    }

    public int getTotalParents() {
        return parentDAO.getTotalParents();
    }

    public boolean hasAssociatedStudents(Long parentId) {
        return parentDAO.hasAssociatedStudents(parentId);
    }

}
