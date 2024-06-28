package eu.telecomnancy.labfx.controller;

import java.io.IOException;

import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class AccueilController {
    @FXML 
    private Button rechercheBouton;

    @FXML
    private Button addItem;
    @FXML
    private Button favoris;
    @FXML
    private TextField rechercheAcceuil;
    private User user;

    public AccueilController(User user) {
        this.user = user;
    }

    public void initalize(){

    }
    @FXML
    void goAddItem(ActionEvent event) {
        Redirection.goAddItem(user, addItem);
    }

    @FXML
    void goFavoris(ActionEvent event) {

    }

    @FXML
    void searchFor(ActionEvent event) {
        Redirection.recherche(user, rechercheAcceuil.getText(),rechercheBouton);
   

    }
}
