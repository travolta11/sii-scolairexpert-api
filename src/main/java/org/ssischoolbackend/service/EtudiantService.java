package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.EtudiantDAO;
import org.ssischoolbackend.dto.EtudiantDto;
import org.ssischoolbackend.model.Etudiant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class EtudiantService {
    private static final Logger log = LoggerFactory.getLogger(EtudiantService.class);
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
        log.info("Class ID in Service: {}", etudiantDTO.getClassId());
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

    public boolean isEmailExists(String email) {
        return etudiantDAO.getEtudiantByEmail(email).isPresent();
    }

    public boolean isPhoneExists(String phoneNumber) {
        return etudiantDAO.getEtudiantByPhone(phoneNumber).isPresent();
    }

    public List<Etudiant> getEtudiantsByClassId(Long classId) {
        return etudiantDAO.getEtudiantsByClassId(classId);
    }

}
