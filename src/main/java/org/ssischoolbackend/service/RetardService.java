package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssischoolbackend.dao.retardDao;
import org.ssischoolbackend.dao.EtudiantDAO;
import org.ssischoolbackend.dao.ParentDao;
import org.ssischoolbackend.dto.RetardDto;
import org.ssischoolbackend.model.retard;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RetardService {

    private final retardDao retardDao;
    private final EtudiantDAO etudiantDAO;
    private final ParentDao parentDao;
    private final EmailService emailService;

    public RetardService(retardDao retardDao, EtudiantDAO etudiantDAO, ParentDao parentDao, EmailService emailService) {
        this.retardDao = retardDao;
        this.etudiantDAO = etudiantDAO;
        this.parentDao = parentDao;
        this.emailService = emailService;
    }

    @Transactional
    public void addRetard(RetardDto retardDTO) {
        retardDao.addRetard(retardDTO);
        sendRetardNotification(retardDTO);
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

    private void sendRetardNotification(RetardDto retardDTO) {
        Long parentId = etudiantDAO.getParentIdByStudentId(retardDTO.getStudentId());
        String parentEmail = parentDao.getEmailByParentId(parentId);
        Optional<String> studentFullNameOptional = etudiantDAO.getFullNameByStudentId(retardDTO.getStudentId());
        String studentFullName = studentFullNameOptional.orElse("Votre enfant");
        String subject = "Notification de retard";
        String text = "Madame, Monsieur,\n\n" +
                "Nous vous informons que votre enfant, " + studentFullName + ", a été en retard le " + retardDTO.getDate() + ".\n" +
                "Nous restons à votre disposition pour toute question ou information complémentaire.\n\n" +
                "Cordialement,\n";

        emailService.sendEmail(parentEmail, subject, text);
    }
}
