package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssischoolbackend.dao.AbsenceDao;
import org.ssischoolbackend.dao.EtudiantDAO;
import org.ssischoolbackend.dao.ParentDao;
import org.ssischoolbackend.dto.AbsenceDto;
import org.ssischoolbackend.model.absence;

import java.util.List;
import java.sql.Date;
import java.util.Optional;

@Service
public class AbsenceService {

    private final AbsenceDao absenceDao;
    private final EtudiantDAO etudiantDAO;
    private final ParentDao parentDao;
    private final EmailService emailService;

    public AbsenceService(AbsenceDao absenceDao, EmailService emailService,EtudiantDAO etudiantDAO, ParentDao parentDao) {
        this.absenceDao = absenceDao;
        this.emailService = emailService;
        this.parentDao = parentDao;
        this.etudiantDAO = etudiantDAO;
    }

    @Transactional
    public void addAbsence(AbsenceDto absenceDTO) {
        absenceDao.addAbsence(absenceDTO);
        sendAbsenceNotification(absenceDTO);
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

    private void sendAbsenceNotification(AbsenceDto absenceDTO) {
        Long parentId = etudiantDAO.getParentIdByStudentId(absenceDTO.getStudentId());
        String parentEmail = parentDao.getEmailByParentId(parentId);
        Optional<String> studentFullNameOptional = etudiantDAO.getFullNameByStudentId(absenceDTO.getStudentId());
        String studentFullName = studentFullNameOptional.orElse("Votre enfant");
        String subject = "Notification d'absence";
        String text = "Madame, Monsieur,\n\n" +
                "Nous vous informons que votre enfant, " + studentFullName + ", a été absent(e) le " + absenceDTO.getDate() + ".\n" +
                "Nous restons à votre disposition pour toute question ou information complémentaire.\n\n" +
                "Cordialement,\n";

        emailService.sendEmail(parentEmail, subject, text);
    }

}
