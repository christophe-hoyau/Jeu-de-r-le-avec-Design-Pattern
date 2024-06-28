package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnexionController {

    @FXML
    private TextField IDField;

    @FXML
    private TextField MdpField;

    @FXML
    private Button connexionButton;

    @FXML
    private Text errorMessage;

    @FXML
    void connexionButtonHandler(ActionEvent event) {
        String identifiant = IDField.getText();
        String motDePasse = MdpField.getText();

        UserController userController = UserController.getInstance();
        User user = userController.testConnexion(identifiant, motDePasse);

        if (user != null) {
            // Connexion réussie
            errorMessage.setText("Connexion réussie");
            errorMessage.setStyle("-fx-fill: green;"); // Couleur verte pour le succès
            // Redirigez vers la page d'accueil avec l'utilisateur connecté
            redirectToAccueil(user);
        } else {
            // Connexion échouée
            errorMessage.setText("Identifiant ou mot de passe incorrect");
            errorMessage.setStyle("-fx-fill: red;"); // Couleur rouge pour l'échec
        }
    }

    @FXML
    void redirectionInscription(ActionEvent event) {
        Redirection.inscription(event);
    }

    @FXML
    void redirectToAccueil(User user) {
        Redirection.accueil(user, connexionButton);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
