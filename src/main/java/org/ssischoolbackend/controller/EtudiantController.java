package org.ssischoolbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.config.ApiResponse;
import org.ssischoolbackend.dto.EtudiantDto;
import org.ssischoolbackend.dto.StaffDto;
import org.ssischoolbackend.model.Etudiant;
import org.ssischoolbackend.model.Student;
import org.ssischoolbackend.service.EtudiantService;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "http://localhost:4200")
public class EtudiantController {
    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Etudiant>> getAllStudents() {
        return ResponseEntity.ok(this.etudiantService.getAllStudents());
    }

    @GetMapping
    public ApiResponse<Etudiant> getAllEtudiants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants(page, size);
        int totalEtudiants = etudiantService.getTotalEtudiants();
        int totalPages = (int) Math.ceil((double) totalEtudiants / size);
        return new ApiResponse<>(etudiants, totalPages);
    }


    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }



    @PostMapping
    public void saveEtudiant(@RequestBody EtudiantDto etudiantDTO) {
        etudiantService.saveEtudiant(etudiantDTO);
    }

    @PutMapping("/{id}")
    public void updateEtudiant(@PathVariable Long id, @RequestBody EtudiantDto etudiantDTO) {
        etudiantService.updateEtudiant(id, etudiantDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/class/{id}")
    public List<Etudiant> getStudentsByClass(@PathVariable Long id) {
        return this.etudiantService.getEtudiantsByClass(id);
    }
}

