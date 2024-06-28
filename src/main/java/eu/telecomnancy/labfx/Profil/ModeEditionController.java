package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class ModeEditionController {

    @FXML
    private PasswordField nouveauMotDePasseField;

    @FXML
    private PasswordField confirmerMotDePasseField;

    @FXML
    private ImageView nouvellePhotoProfil;


    private User user;

    public ModeEditionController(User user) {
        this.user = user;
    }

    // Méthode associée à l'action "Charger Nouvelle Photo"
    // @FXML
    public void chargerNouvellePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une nouvelle photo de profil");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Mettez à jour l'image de profil avec la nouvelle image sélectionnée
            user.setImage(selectedFile.getAbsolutePath());
            afficherPhotoProfil();
            DirectoryHandler.saveNewUserImage(user, selectedFile.getAbsolutePath());
            // Affichez le message à l'utilisateur
            afficherMessage("Nouvelle photo de profil ajoutée");
        }
    }

    private void afficherPhotoProfil() {
        // Chargez l'image depuis le chemin stocké dans l'utilisateur
        Image image = new Image("file:" + user.getImage());
        nouvellePhotoProfil.setImage(image);
    }

    private void afficherMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
