package org.ssischoolbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.service.EspaceService;

import org.ssischoolbackend.dto.EspaceDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/espace")
public class EsapceController {

    @Autowired
    private EspaceService espaceService;

    @GetMapping("/all")
    public ResponseEntity<List<EspaceDto>> getAllEspaces() {
        return ResponseEntity.ok(this.espaceService.getAllEsapces());
    }

    @PostMapping("")
    public long createNewEspace(@RequestBody EspaceDto espaceDto) {
        return this.espaceService.createEspace(espaceDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspaceDto> getEspaceById(@PathVariable("id") Long id){
        Optional<EspaceDto> espaceDto= this.espaceService.getEspaceByID(id);
        if(espaceDto.isPresent()){
            return new ResponseEntity<>(espaceDto.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
