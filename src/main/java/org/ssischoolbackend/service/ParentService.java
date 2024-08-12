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

    public Parent getParentByCin(String cin) {
        return parentDAO.getParentByCin(cin).orElse(null);
    }

    public void saveParent(ParentDto parentDTO) {
        Parent parent = new Parent();
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setPhone(parentDTO.getPhone());
        parent.setEmail(parentDTO.getEmail());
        parent.setCin(parentDTO.getCin());
        parentDAO.createNewParent(parent);
    }

    public void updateParent(Long id, ParentDto parentDTO) {
        Parent parent = new Parent();
        parent.setId(id);
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setPhone(parentDTO.getPhone());
        parent.setEmail(parentDTO.getEmail());
        parent.setCin(parentDTO.getCin());
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

    public boolean isCinExists(String cin) {
        return parentDAO.getParentByCin(cin).isPresent();
    }

    public boolean isEmailExists(String email) {
        return parentDAO.getParentByEmail(email).isPresent();
    }

    public boolean isPhoneExists(String phone) {
        return parentDAO.getParentByPhone(phone).isPresent();
    }

}
