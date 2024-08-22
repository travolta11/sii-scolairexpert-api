package org.ssischoolbackend.controller;

import org.ssischoolbackend.dto.MaterielDto;
import org.ssischoolbackend.service.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materiel")
public class MaterielController {

    @Autowired
    private MaterielService materielService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMateriel(@RequestBody MaterielDto materielDto) {
        materielService.createMateriel(materielDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaterielDto>> getAllMateriel() {
        List<MaterielDto> materiels = materielService.getAllMateriel();
        return ResponseEntity.ok(materiels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterielDto> getMaterielById(@PathVariable Long id) {
        Optional<MaterielDto> materielOptional = materielService.getMaterielById(id);
        return materielOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateMateriel(@PathVariable Long id, @RequestBody MaterielDto materielDto) {
        materielService.updateMateriel(id, materielDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMateriel(@PathVariable Long id) {
        materielService.deleteMateriel(id);
        return ResponseEntity.ok().build();
    }
}
