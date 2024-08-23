package org.ssischoolbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.dto.AbsenceDto;
import org.ssischoolbackend.model.absence;
import org.ssischoolbackend.service.AbsenceService;

import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("/api/absences")
public class AbsenceContoller {

    private final AbsenceService absenceService;

    public AbsenceContoller(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAbsence(@RequestBody AbsenceDto absenceDTO) {
        absenceService.addAbsence(absenceDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<absence>> getAbsencesByDate(@PathVariable Date date) {
        List<absence> absences = absenceService.getAbsencesByDate(date);
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<absence>> getAbsencesByStudentId(@PathVariable int studentId) {
        List<absence> absences = absenceService.getAbsencesByStudentId(studentId);
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/date/{date}")
    public ResponseEntity<absence> getAbsenceByStudentIdAndDate(@PathVariable int studentId, @PathVariable Date date) {
        absence absence = absenceService.getAbsenceByStudentIdAndDate(studentId, date);
        return new ResponseEntity<>(absence, HttpStatus.OK);
    }


    @PutMapping("/update/reason")
    public ResponseEntity<Void> updateAbsenceReason(@RequestBody AbsenceDto absenceDTO) {
        absenceService.updateAbsenceReason(absenceDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
