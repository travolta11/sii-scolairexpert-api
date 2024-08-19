package org.ssischoolbackend.service;

import org.ssischoolbackend.dao.MaterielDao;
import org.ssischoolbackend.dto.MaterielDto;
import org.ssischoolbackend.model.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterielService {

    @Autowired
    private MaterielDao materielDao;

    public void createMateriel(MaterielDto materielDto) {
        Materiel materiel = Materiel.builder()
                .code(materielDto.getCode())
                .type(materielDto.getType())
                .status(materielDto.getStatus())
                .build();
        materielDao.createMateriel(materiel);
    }

    public List<MaterielDto> getAllMateriel() {
        return materielDao.getAllMateriel().stream()
                .map(MaterielDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<MaterielDto> getMaterielById(Long id) {
        Optional<Materiel> materielOptional = materielDao.getMaterielById(id);
        return materielOptional.map(MaterielDto::fromEntity);
    }

    public void updateMateriel(Long id, MaterielDto materielDto) {
        Materiel materiel = Materiel.builder()
                .id(id)
                .code(materielDto.getCode())
                .type(materielDto.getType())
                .status(materielDto.getStatus())
                .build();
        materielDao.updateMateriel(id, materiel);
    }

    public void deleteMateriel(Long id) {
        materielDao.deleteMateriel(id);
    }
}
