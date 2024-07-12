package org.ssischoolbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.ssischoolbackend.dao.EspaceDao;
import org.ssischoolbackend.dto.EspaceDto;
import org.ssischoolbackend.model.Espace;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspaceService {
    @Autowired
    private EspaceDao espaceDao;

    private final ModelMapper modelMapper = new ModelMapper();

    public Long createEspace(EspaceDto espaceDto) {
        return this.espaceDao.createNewEspace(this.getEspace(espaceDto));
    }

    public Optional<EspaceDto> getEspaceByID(Long id) {
        Optional<Espace> espaceOptional = this.espaceDao.getEspaceById(id);
        return espaceOptional.map(this::getEspaceDto);
    }

    public List<EspaceDto> getAllEsapces() {
        return this.espaceDao.getAllEspaces().stream().map(this::getEspaceDto).collect(Collectors.toList());
    }

    private Espace getEspace(EspaceDto espaceDto) {
        return modelMapper.map(espaceDto, Espace.class);
    }

    private EspaceDto getEspaceDto(Espace espace) {
        return modelMapper.map(espace, EspaceDto.class);
    }



}
