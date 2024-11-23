package org.ssischoolbackend.controller;


import org.optaplanner.core.api.solver.SolverStatus;
import org.ssischoolbackend.dao.TimetableDao;
import org.ssischoolbackend.model.Timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import org.ssischoolbackend.model.Timetable;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolutionManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableDao timetableDao;
    @Autowired
    private SolverManager<Timetable, Long> solverManager;
    @Autowired
    private SolutionManager<Timetable, HardSoftScore> solutionManager;

    @GetMapping
    public Timetable getTimeTable() {
        SolverStatus solverStatus = getSolverStatus();
        Timetable solution = timetableDao.findById(timetableDao.SINGLETON_TIME_TABLE_ID);
        solutionManager.update(solution);
        solution.setSolverStatus(solverStatus);
        return solution;
    }

    @PostMapping("/solve")
    public void solve() {
        solverManager.solveAndListen(timetableDao.SINGLETON_TIME_TABLE_ID,
        timetableDao::findById,
        timetableDao::save);
    }

    @PostMapping("/stopSolving")
    public void stopSolving() {
        solverManager.terminateEarly(timetableDao.SINGLETON_TIME_TABLE_ID);
    }
    @GetMapping("/status")
    public SolverStatus getSolverStatus() {
        return solverManager.getSolverStatus(timetableDao.SINGLETON_TIME_TABLE_ID);
    }
}

