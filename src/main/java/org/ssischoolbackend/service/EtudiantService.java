package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.EtudiantDAO;
import org.ssischoolbackend.dto.EtudiantDto;
import org.ssischoolbackend.model.Etudiant;

import java.util.List;

@Service
public class EtudiantService {
    private final EtudiantDAO etudiantDAO;

    public EtudiantService(EtudiantDAO etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }

    public List<Etudiant> getAllEtudiants(int page, int size) {
        int offset = page * size;
        return etudiantDAO.getAllEtudiants(offset, size);
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantDAO.getEtudiantById(id).orElse(null);
    }

    public void saveEtudiant(EtudiantDto etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(etudiantDTO.getFirstName());
        etudiant.setLastName(etudiantDTO.getLastName());
        etudiant.setEmail(etudiantDTO.getEmail());
        etudiant.setPhoneNumber(etudiantDTO.getPhoneNumber());
        etudiant.setAddress(etudiantDTO.getAddress());
        etudiant.setZipCode(etudiantDTO.getZipCode());
        etudiant.setGender(etudiantDTO.getGender());
        etudiant.setLevel(etudiantDTO.getLevel());
        etudiant.setDateOfBirth(etudiantDTO.getDateOfBirth());
        etudiant.setParentId(etudiantDTO.getParentId());
        etudiant.setClassId(etudiantDTO.getClassId());
        etudiantDAO.createNewEtudiant(etudiant);
    }

    public void updateEtudiant(Long id, EtudiantDto etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(etudiantDTO.getFirstName());
        etudiant.setLastName(etudiantDTO.getLastName());
        etudiant.setEmail(etudiantDTO.getEmail());
        etudiant.setPhoneNumber(etudiantDTO.getPhoneNumber());
        etudiant.setAddress(etudiantDTO.getAddress());
        etudiant.setZipCode(etudiantDTO.getZipCode());
        etudiant.setGender(etudiantDTO.getGender());
        etudiant.setLevel(etudiantDTO.getLevel());
        etudiant.setDateOfBirth(etudiantDTO.getDateOfBirth());
        etudiant.setParentId(etudiantDTO.getParentId());
        etudiant.setClassId(etudiantDTO.getClassId());
        etudiantDAO.updateEtudiant(etudiant);
    }

    public void deleteEtudiant(Long id) {
        etudiantDAO.deleteEtudiantById(id);
    }

    public int getTotalEtudiants() {
        return etudiantDAO.getTotalEtudiants();
    }
}
