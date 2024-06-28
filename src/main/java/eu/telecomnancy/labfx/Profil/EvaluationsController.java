package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.Evaluation;
import eu.telecomnancy.labfx.utils.EvaluationController;
import eu.telecomnancy.labfx.utils.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class EvaluationsController {

    @FXML
    private Label moyenneEvaluationsLabel;

    @FXML
    private ListView<String> evaluationsListView;

    private User user;
    private ArrayList<Evaluation> receivedEvaluations;

    public EvaluationsController(User user) {
        this.user = user;
        this.receivedEvaluations = new ArrayList<>();
    }

    public void initialize() {
        // Récupérez les évaluations récentes de l'utilisateur
        ArrayList<Evaluation> recentEvaluations = UserController.getInstance().getEvaluationsSorted(user);

        // Créez une liste observable pour stocker les éléments de l'historique récent
        ObservableList<String> evaluationItems = FXCollections.observableArrayList();
        System.out.println("EvaluationController");
        // Ajoutez chaque élément de l'historique récent dans la liste observable
        for (Evaluation evaluationItem : recentEvaluations) {
            System.out.println(evaluationItem.getRating() + " - " + evaluationItem.getComment() + " - " + evaluationItem.getCreatedAt().toString().replace("T", " "));
            StringBuilder evaluationItemString = new StringBuilder();
            evaluationItemString.append(evaluationItem.getRating()).append(" - ").append(evaluationItem.getComment()).append(" - ").append(evaluationItem.getCreatedAt().toString().replace("T", " "));
            evaluationItems.add(evaluationItemString.toString());
        }

        // Configurez la ListView avec la liste observable
        evaluationsListView.setItems(evaluationItems);
    }
}
