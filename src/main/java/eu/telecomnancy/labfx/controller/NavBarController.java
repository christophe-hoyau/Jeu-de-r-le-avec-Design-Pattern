package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class NavBarController {

    @FXML
    private Button balance;
    @FXML
    private Button home;
    @FXML
    private Button messagerie;
    @FXML
    private Button profil;
    @FXML
    private TextField searchBar;
    @FXML
    private ImageView searchImg;
    @FXML
    private Button deconnexion;

    private User user;

    public NavBarController(User user) {
        this.user = user;
    }
    @FXML
    public void initialize() {
        if(user != null) {
            balance.setText(String.valueOf(user.getFlorains()));
        }
    }
    @FXML
    void goBalance(ActionEvent event) {
    }

    @FXML
    void goHome(ActionEvent event) {
        Redirection.accueil(user, home);
    }

    @FXML
    void goMessagerie(ActionEvent event) {

    }

    @FXML
    void goProfil(ActionEvent event) {
        Redirection.goProfil(user, profil);
    }

    @FXML
    void deconnect(ActionEvent event) {
        Redirection.connexion(event);
    }

    @FXML
    void searchFor(ActionEvent event) {

    }

}
