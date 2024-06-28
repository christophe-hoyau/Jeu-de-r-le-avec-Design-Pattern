package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.History;
import eu.telecomnancy.labfx.user.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class HistoriqueRecentController {

    @FXML
    private ListView<String> recentHistoryListView; // La ListView pour afficher l'historique récent

    private User user;
    private UserController userController;

    public HistoriqueRecentController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        // Récupérez l'historique récent de l'utilisateur
        ArrayList<History> recentHistory = UserController.getInstance().getRecentHistory(user);

        // Créez une liste observable pour stocker les éléments de l'historique récent
        ObservableList<String> historyItems = FXCollections.observableArrayList();

        // Ajoutez chaque élément de l'historique récent dans la liste observable
        for (History historyItem : recentHistory) {
            String historyItemString = historyItem.getMaterialService().getName() +
                    " - : " + historyItem.getMaterialService().getType() +
                    " -  cout : " + historyItem.getPrice() + "€" +
                    " -  date : " + historyItem.getMaterialService();
            historyItems.add(historyItemString);
        }

        // Configurez la ListView avec la liste observable
        recentHistoryListView.setItems(historyItems);
    }
}
