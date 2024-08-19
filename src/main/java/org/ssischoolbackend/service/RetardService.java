package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssischoolbackend.dao.retardDao;
import org.ssischoolbackend.dto.RetardDto;
import org.ssischoolbackend.model.retard;

import java.sql.Date;
import java.util.List;

@Service
public class RetardService {

    private final retardDao retardDao;

    public RetardService(retardDao retardDao) {
        this.retardDao = retardDao;
    }

    @Transactional
    public void addRetard(RetardDto retardDTO) {
        retardDao.addRetard(retardDTO);
    }

    @Transactional(readOnly = true)
    public List<retard> getRetardsByDate(Date date) {
        return retardDao.getRetardByDate(date);
    }

    @Transactional(readOnly = true)
    public List<retard> getRetardsByStudentId(Long studentId) {
        return retardDao.getRetardByStudentId(studentId);
    }

    @Transactional
    public void updateRetardReason(RetardDto retardDTO) {
        retardDao.updateRetardReason(retardDTO);
    }

    @Transactional(readOnly = true)
    public retard getRetardByStudentIdAndDate(Long studentId, Date date) {
        return retardDao.getRetardByStudentIdAndDate(studentId, date);
    }

}
