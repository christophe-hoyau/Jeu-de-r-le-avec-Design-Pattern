package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.user.ClassicUser;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InfoPersoController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField cityField;

    private User user;

    public InfoPersoController(User user) {
        this.user = user;
    }

    public void initialize() {
        // Afficher les informations de l'utilisateur dans les champs appropriés
        identifiantField.setText(user.getIdentifiant());
        passwordField.setText(user.getPassword());
        lastnameField.setText(user.getLastName());
        firstnameField.setText(user.getFirstName());
        emailField.setText(user.getEmail());
        cityField.setText(user.getCity());
    }

    @FXML
    void sauvegarderInfoPerso(ActionEvent event) {
        // Récupérez les valeurs des champs
        String identifiant = identifiantField.getText();
        String password = passwordField.getText();
        String lastname = lastnameField.getText();
        String firstname = firstnameField.getText();
        String email = emailField.getText();
        String city = cityField.getText();

        // Créez un nouvel objet ClassicUser avec les nouvelles valeurs
        ClassicUser updatedUser = new ClassicUser(
                user.getId(), identifiant, password, lastname, firstname, email,
                city, user.getFlorains(), user.getCreatedAt(), user.getImage(),
                user.getDescription(), user.getItemsOwned(), user.getFavouriteItems(),
                user.getEvaluations(), user.getItemsSell(), user.getItemsBuy()
        );

        // Mettez à jour l'utilisateur dans le UserController
        UserController.getInstance().updateUser(updatedUser);

        // Affichez le message à l'utilisateur
        afficherMessage("Changements sauvegardés");

        System.out.println("Informations personnelles sauvegardées : " +
                identifiant + ", " + password + ", " + lastname + ", " +
                firstname + ", " + email + ", " + city);
    }

    private void afficherMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
