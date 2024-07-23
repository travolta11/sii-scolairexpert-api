package org.ssischoolbackend.controller;

import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.dao.EtudiantDAO;
import org.ssischoolbackend.config.ApiResponse;
import org.ssischoolbackend.dto.EtudiantDto;
import org.ssischoolbackend.model.Etudiant;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    private final EtudiantDAO etudiantDAO;

    public EtudiantController(EtudiantDAO etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }

    @GetMapping
    public ApiResponse<Etudiant> getAllEtudiants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        int offset = page * size;
        List<Etudiant> etudiants = etudiantDAO.getAllEtudiants(offset, size);
        int totalEtudiants = etudiantDAO.getTotalEtudiants();
        int totalPages = (int) Math.ceil((double) totalEtudiants / size);
        return new ApiResponse<>(etudiants, totalPages);
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id) {
        return etudiantDAO.getEtudiantById(id);
    }

    @PostMapping
    public void saveEtudiant(@RequestBody EtudiantDto etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(etudiantDTO.getFirstName());
        etudiant.setLastName(etudiantDTO.getLastName());
        etudiant.setEmail(etudiantDTO.getEmail());
        etudiant.setPhoneNumber(etudiantDTO.getPhoneNumber());
        etudiant.setAdress(etudiantDTO.getAdress());
        etudiant.setZipCode(etudiantDTO.getZipCode());
        etudiant.setGender(etudiantDTO.getGender());
        etudiant.setLevel(etudiantDTO.getLevel());
        etudiant.setClasse(etudiantDTO.getClasse());
        etudiantDAO.save(etudiant);
    }

    @PutMapping("/{id}")
    public void updateEtudiant(@PathVariable Long id, @RequestBody EtudiantDto etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(etudiantDTO.getFirstName());
        etudiant.setLastName(etudiantDTO.getLastName());
        etudiant.setEmail(etudiantDTO.getEmail());
        etudiant.setPhoneNumber(etudiantDTO.getPhoneNumber());
        etudiant.setAdress(etudiantDTO.getAdress());
        etudiant.setZipCode(etudiantDTO.getZipCode());
        etudiant.setGender(etudiantDTO.getGender());
        etudiant.setLevel(etudiantDTO.getLevel());
        etudiant.setClasse(etudiantDTO.getClasse());
        etudiantDAO.update(etudiant);
    }

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable Long id) {
        etudiantDAO.deleteById(id);
    }
}
