package org.ssischoolbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.dto.RetardDto;
import org.ssischoolbackend.model.retard;
import org.ssischoolbackend.service.RetardService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/retards")
public class RetardController {

    private final RetardService retardService;

    public RetardController(RetardService retardService) {
        this.retardService = retardService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createRetard(@RequestBody RetardDto retardDTO) {
        retardService.addRetard(retardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/byDate")
    public ResponseEntity<List<retard>> getRetardsByDate(@RequestParam("date") Date date) {
        List<retard> retards = retardService.getRetardsByDate(date);
        return ResponseEntity.ok(retards);
    }

    @GetMapping("/byStudentId")
    public ResponseEntity<List<retard>> getRetardsByStudentId(@RequestParam("studentId") Long studentId) {
        List<retard> retards = retardService.getRetardsByStudentId(studentId);
        return ResponseEntity.ok(retards);
    }

    @GetMapping("/student/{studentId}/date/{date}")
    public ResponseEntity<retard> getRetardByStudentIdAndDate(@PathVariable Long studentId, @PathVariable Date date) {
        retard retard = retardService.getRetardByStudentIdAndDate(studentId, date);
        return new ResponseEntity<>(retard, HttpStatus.OK);
    }


    @PutMapping("/updateReason")
    public ResponseEntity<Void> updateRetardReason(@RequestBody RetardDto retardDTO) {
        retardService.updateRetardReason(retardDTO);
        return ResponseEntity.ok().build();
    }
}
