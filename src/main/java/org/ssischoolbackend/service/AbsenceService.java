package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssischoolbackend.dao.AbsenceDao;
import org.ssischoolbackend.dto.AbsenceDto;
import org.ssischoolbackend.model.absence;

import java.util.List;
import java.sql.Date;

@Service
public class AbsenceService {

    private final AbsenceDao absenceDao;

    public AbsenceService(AbsenceDao absenceDao) {
        this.absenceDao = absenceDao;
    }

    @Transactional
    public void addAbsence(AbsenceDto absenceDTO) {
        absenceDao.addAbsence(absenceDTO);
    }

    @Transactional(readOnly = true)
    public List<absence> getAbsencesByDate(Date date) {
        return absenceDao.getAbsenceByDate(date);
    }

    @Transactional(readOnly = true)
    public List<absence> getAbsencesByStudentId(int studentId) {
        return absenceDao.getAbsenceByStudentId(studentId);
    }

    @Transactional
    public void updateAbsenceReason(AbsenceDto absenceDTO) {
        absenceDao.updateAbsenceReason(absenceDTO);
    }

    @Transactional(readOnly = true)
    public absence getAbsenceByStudentIdAndDate(int studentId, Date date) {
        return absenceDao.getAbsenceByStudentIdAndDate(studentId, date);
    }
}
