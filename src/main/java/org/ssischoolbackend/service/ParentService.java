package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.ParentDao;
import org.ssischoolbackend.dao.UserDao;
import org.ssischoolbackend.dto.ParentDto;
import org.ssischoolbackend.model.Parent;
import org.ssischoolbackend.model.User;

import java.util.List;

@Service
public class ParentService {
    private final ParentDao parentDAO;
    private final UserDao userDAO;
    private final EmailService emailService;

    public ParentService(ParentDao parentDAO, UserDao userDAO, EmailService emailService) {
        this.parentDAO = parentDAO;
        this.userDAO = userDAO;
        this.emailService = emailService;
    }

    public List<Parent> getAllParents(int page, int size) {
        int offset = page * size;
        return parentDAO.getAllParents(offset, size);
    }

    public Parent getParentById(Long id) {
        return parentDAO.getParentById(id).orElse(null);
    }

    public Parent getParentByCin(String cin) {
        return parentDAO.getParentByCin(cin).orElse(null);
    }

    public void saveParent(ParentDto parentDTO) {
        // Créer un objet Parent
        Parent parent = new Parent();
        parent.setFirstName(parentDTO.getFirstName());
        parent.setLastName(parentDTO.getLastName());
        parent.setPhone(parentDTO.getPhone());
        parent.setEmail(parentDTO.getEmail());
        parent.setCin(parentDTO.getCin());

        // Créer un utilisateur associé au parent
        String username = parent.getFirstName().toLowerCase() + "." + parent.getLastName().toLowerCase();
        String password = parent.getCin() + "123";

        User user = User.builder()
                .username(username)
                .password(password)
                .email(parent.getEmail())
                .role("parent")
                .build();

        // Enregistrer l'utilisateur dans la base de données
        long userId = userDAO.createUser(user);

        // Affecter l'ID utilisateur au parent et enregistrer le parent
        parent.setUserId(userId);
        long parentId = parentDAO.createNewParent(parent);

        // Envoyer les informations de compte par email
        sendAccountInfoEmail(parent.getEmail(), username, password);
    }


    public void updateParent(Long id, ParentDto parentDTO) {
        // Récupérer le parent actuel
        Parent parent = parentDAO.getParentById(id).orElse(null);
        if (parent != null) {
            // Mettre à jour les informations du parent
            parent.setFirstName(parentDTO.getFirstName());
            parent.setLastName(parentDTO.getLastName());
            parent.setPhone(parentDTO.getPhone());
            parent.setEmail(parentDTO.getEmail());
            parent.setCin(parentDTO.getCin());
            parentDAO.updateParent(parent);
            String password = parent.getCin() + "123";

            // Mettre à jour les informations de l'utilisateur associé
            User user = User.builder()
                    .id(parent.getUserId())
                    .username(parent.getFirstName().toLowerCase() + "." + parent.getLastName().toLowerCase())
                    .email(parent.getEmail())
                    .password(password)  // Ne pas changer le mot de passe
                    .role("parent")
                    .build();
            userDAO.updateUser(parent.getUserId(), user);  // Passez l'ID utilisateur et l'objet User

            // Envoyer les informations de compte par email
            sendAccountInfoEmail(parent.getEmail(), user.getUsername(), password);
        }
    }

    public void deleteParent(Long id) {
        // Récupérer le parent actuel
        Parent parent = parentDAO.getParentById(id).orElse(null);
        if (parent != null) {
            // Supprimer le parent
            parentDAO.deleteParentById(id);

            // Supprimer l'utilisateur associé
            userDAO.deleteUser(parent.getUserId());
        }
    }

    public int getTotalParents() {
        return parentDAO.getTotalParents();
    }

    public boolean hasAssociatedStudents(Long parentId) {
        return parentDAO.hasAssociatedStudents(parentId);
    }

    public boolean isCinExists(String cin) {
        return parentDAO.getParentByCin(cin).isPresent();
    }

    public boolean isEmailExists(String email) {
        return parentDAO.getParentByEmail(email).isPresent();
    }

    public boolean isPhoneExists(String phone) {
        return parentDAO.getParentByPhone(phone).isPresent();
    }

    private void sendAccountInfoEmail(String email, String username, String password) {
        String subject = "Informations de compte";
        String text = "Bonjour,\n\n" +
                "Votre compte parent a été créé/mis à jour avec les informations suivantes :\n" +
                "Nom d'utilisateur : " + username + "\n" +
                "Mot de passe : " + password + "\n\n" +
                "Veuillez conserver ces informations en lieu sûr.\n\n" +
                "Cordialement,\n" +
                "L'équipe administrative.";

        emailService.sendEmail(email, subject, text);
    }

}
