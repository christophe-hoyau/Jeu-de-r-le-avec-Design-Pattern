package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InscriptionController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField motDePasseField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField villeField;

    @FXML
    void inscriptionButtonHandler(ActionEvent event) {
        // On utilise les valeurs des champs pour effectuer une vérification
        String email = emailField.getText();
        String identifiant = identifiantField.getText();
        String motDePasse = motDePasseField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String ville = villeField.getText();

        // Vérification basique de connexion
        if (email.isEmpty() || identifiant.isEmpty() || motDePasse.isEmpty() || nom.isEmpty() || prenom.isEmpty() || ville.isEmpty()) {
            // Connexion échouée
            System.out.println("Veuillez remplir tous les champs");
        } else {
            // Connexion réussie
            System.out.println("Inscription réussie");

            // Utilisez le UserController pour créer un nouvel utilisateur
            UserController userController = UserController.getInstance();
            userController.createClassicUser(identifiant, motDePasse, prenom, nom, email, ville, 100, "", "");

            // Redirigez vers l'onglet de connexion après une inscription réussie
            redirectToConnexion(event);
        }
    }

    private void redirectToConnexion(ActionEvent event) {
        Redirection.connexion(event);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
