package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialController;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

import eu.telecomnancy.labfx.MaterialService.Material;
import eu.telecomnancy.labfx.MaterialService.ServiceController;
import eu.telecomnancy.labfx.MaterialService.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;





public class ResultatsController {

      @FXML
    private void handleAjouterAuxFavoris(ActionEvent event) {
        // Logique pour ajouter aux favoris
    }
    @FXML
    private void handleVoirLeProduit(ActionEvent event) {
        // Logique pour voir le service
    }
    @FXML
    private TextField searchBar;

    @FXML
    private ComboBox<String> displayModeComboBox;

    @FXML
    private TilePane resultsTilePane;
    @FXML
    private TextField rechercheAcceuil;
    @FXML
    private ListView<VBox> resultsListView;
    @FXML
    private Label searchLabel;
    @FXML
    private Button switchResultsButton;
    private MaterialController materialController = MaterialController.getInstance();
    private ServiceController serviceController = ServiceController.getInstance();
    private Material material ;
    private Service service ;
    private int[][] ListeIdScoreService;
    private int[][] ListeIdScoreMateriel;

    public ResultatsController(){
        
    }
    @FXML
    protected void  handleRechercheButtonClick() {
        String searchTerm = searchBar.getText();
        initializeResultsService(searchTerm);
        initializeResultsMateriel(searchTerm);
    }

    public int[][] initializeResultsMateriel(String searchTerm) {

      
       
        int maxMaterialId = materialController.getMaxId();
        // on calcule le nombre de services et de materiels
     
        int nbrMateriels = 0;
       
        for (int i=1; i<1+maxMaterialId; i++){
            if (materialController.get(i) != null) {
                nbrMateriels++;
            }
        }
 
       
        ListeIdScoreMateriel = new int[nbrMateriels][2];
        
        
    
        
       
        int b=0;
        for (int i = 0; i < maxMaterialId; i++) {
            if (materialController.get(i + 1) != null) {
                material= (Material) materialController.get(i + 1);
                if(material!=null || material.isActive() || material.getId()!=0){
                ListeIdScoreMateriel[b][0] = material.getId();
                ListeIdScoreMateriel[b][1] = 0;
                b++;
                }
            }
        }

        motEstDansClasseMateriel(searchTerm, ListeIdScoreMateriel);
       
       
        return trierScore(ListeIdScoreMateriel);
        
        
}
public int[][] initializeResultsService(String searchTerm) {

  
    int maxServiceId = serviceController.getMaxId();
    
    int nbrServices = 0;
  
    for (int i=1; i<1+maxServiceId; i++){
        if (serviceController.get(i) != null) {
            nbrServices++;
        }
    }
    

    ListeIdScoreService = new int[nbrServices][2];

    
    
       
    int a=0;
    for (int i = 0; i <= maxServiceId; i++) {
        if (serviceController.get(i+1) != null) {
            service = (Service)serviceController.get(i+1);
            if(service!=null || service.isActive() || service.getId()!=0){
           
            ListeIdScoreService[a][0] = service.getId();
            ListeIdScoreService[a][1] = 0;
            a++;
            }
        }
    }
    
  
    motEstDansClasseService(searchTerm, ListeIdScoreService);
    return trierScore(ListeIdScoreService);
   
}

        
        
        


    

    public void separerMotRecherche(String searchTerm) {
        String[] mots = searchTerm.split(" ");
    
        for (int i = 0; i < mots.length; i++) {
            mots[i] = mots[i].toLowerCase();
        }
    }

    public static int[][] trierScore(int[][] idScore) {
        Arrays.sort(idScore, (a, b) -> Integer.compare(b[1], a[1]));

        int countNonZero = 0;
        for (int i = 0; i < idScore.length; i++) {
            if (idScore[i][1] != 0) {
                countNonZero++;
            }
        }

        int[][] sortedIdScore = new int[countNonZero][2];
        System.arraycopy(idScore, 0, sortedIdScore, 0, countNonZero);

        return sortedIdScore;
    }
   

    public void afficherResultatsServiceFirst(int[][] idScoreService, int[][] idScoreMateriel, ListView<VBox> listView) {
        for (int i = 0; i < idScoreService.length; i++) {
            if (idScoreService[i][1] != 0) {
                listView.getItems().add(createCustomComponentService(idScoreService[i][0], "Service"));
            }
        }
        for (int i = 0; i < idScoreMateriel.length; i++) {
            if (idScoreMateriel[i][1] != 0) {
                listView.getItems().add(createCustomComponentMateriel(idScoreMateriel[i][0], "Materiel"));
            }
        }
    }
    public void afficherResultatsMaterielFirst(int[][] idScoreService, int[][] idScoreMateriel,ListView<VBox> listView) {
        for (int i = 0; i < idScoreMateriel.length; i++) {
            if (idScoreMateriel[i][1] != 0) {
                listView.getItems().add(createCustomComponentMateriel(idScoreMateriel[i][0], "Materiel"));

            }
        }
        for (int i = 0; i < idScoreService.length; i++) {
            if (idScoreService[i][1] != 0) {
                listView.getItems().add(createCustomComponentService(idScoreService[i][0], "Service"));
               
            }
        }
    }
    public void afficherResultats(int[][] idScoreService, int[][] idScoreMateriel,ListView<VBox> listView) {
        int a = 0;
        int b = 0;
        while (a < idScoreMateriel.length || b < idScoreService.length){
            if(a>=idScoreMateriel.length){
                listView.getItems().add(createCustomComponentService(idScoreService[b][0], "Service"));
                if (b < idScoreService.length ) {
                    b++;
                }
            }
            else if(b>=idScoreService.length){
                listView.getItems().add(createCustomComponentMateriel(idScoreMateriel[a][0], "Materiel"));
                if (a < idScoreMateriel.length ) {
                    a++;
                }
            }
            else if(idScoreMateriel[a][1]>idScoreService[b][1]){
                listView.getItems().add(createCustomComponentMateriel(idScoreMateriel[a][0], "Materiel"));
                a++;
                
                
            }
            else{
                listView.getItems().add(createCustomComponentService(idScoreService[b][0], "Service"));
                b++;
                
            }
            
            
        }
  
    }
    

    private VBox createCustomComponentMateriel(int itemId, String itemType) {
        material = (Material) materialController.get(itemId);
    
        ImageView imageView = new ImageView(/*... */);
        Label nameLabel = new Label(itemType + " : " + material.getName());
        Label descriptionLabel = new Label("Description :" + material.getDescription());
        Label costLabel = new Label("Coût : " + material.getCost() + "€");
    
        // Bouton "Ajouter aux favoris"
        Button addToFavoritesButton = new Button("Ajouter aux favoris");
        addToFavoritesButton.setOnAction(event -> {
            // Logique pour ajouter aux favoris
            handleAjouterAuxFavoris(event);
        });
    
        // Bouton "Voir le Matériel"
        Button viewMaterialButton = new Button("Voir le Matériel");
        viewMaterialButton.setOnAction(event -> {
            handleVoirLeProduit(event);
            // Logique pour voir le matériel
        });
    
        VBox customComponent = new VBox(imageView, nameLabel, descriptionLabel, costLabel, addToFavoritesButton, viewMaterialButton);
        customComponent.setSpacing(8);
    
        return customComponent;
    }
    
    private VBox createCustomComponentService(int itemId, String itemType) {
        service = (Service) serviceController.get(itemId);
    
        ImageView imageView = new ImageView(/*... */);
        Label nameLabel = new Label(itemType + " : " + service.getName());
        Label descriptionLabel = new Label(service.getDescription());
        Label costLabel = new Label("Coût : " + service.getCost() + "€");
    
        Button addToFavoritesButton = new Button("Ajouter aux favoris");
        addToFavoritesButton.setOnAction(event -> {
            // Ajouter la logique pour ajouter aux favoris
            handleAjouterAuxFavoris(event);
        });
    
        Button viewServiceButton = new Button("Voir le service");
        viewServiceButton.setOnAction(event -> {
            handleVoirLeProduit(event);
            // Ajouter la logique pour voir le service
        });
    
        VBox customComponent = new VBox(imageView, nameLabel, descriptionLabel, costLabel, addToFavoritesButton, viewServiceButton);
        customComponent.setSpacing(8); 
    
        return customComponent;
    }

    
    public int max(int a, int b) {
        return a > b ? a : b;
    }
    // mot est une chaine de caractere
    public void motEstDansClasseService(String mot, int[][] idScoreService) {
        String[] mots1 = mot.split(" ");
        for (int i = 0; i < idScoreService.length; i++) {
            int itemId = idScoreService[i][0];
            service =(Service) serviceController.get(itemId);
    
            if (service != null && service.getName() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], service.getName()) == 1) {
                        idScoreService[i][1] += 1;
                    }
                }
            }
            if (service != null && service.getDescription() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], service.getDescription()) == 1) {
                        idScoreService[i][1] += 1;
                    }
                }
            }
            if (service != null && service.getType()!=null){
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], service.getType()) == 1) {
                        idScoreService[i][1] += 1;
                    }
                }
            }
            if (service != null && !service.isActive()){
                idScoreService[i][1] = 0;
            }
        }
    }
    
    public void motEstDansClasseMateriel(String mot, int[][] idScoreMateriel) {
        String[] mots1 = mot.split(" ");
        for (int i = 0; i < idScoreMateriel.length; i++) {
            int itemId = idScoreMateriel[i][0];
            material =(Material) materialController.get(itemId);
    
            if (material != null && material.getName() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], material.getName()) == 1) {
                        idScoreMateriel[i][1] += 1;
                    }
                }
            }
            if (material != null && material.getDescription() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], material.getDescription()) == 1) {
                        idScoreMateriel[i][1] += 1;
                    }
                }
            }
            if (material != null && material.getType()!=null){
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], material.getType()) == 1) {
                        idScoreMateriel[i][1] += 1;
                    }
                }
            }
            if (material != null && material.isActive()==false){
                idScoreMateriel[i][1] = 0;
            }
        }
    }
    
   public int estDedans(String mot, String chainesDeCaracteres) {
        String[] mots = chainesDeCaracteres.toLowerCase().split(" ");
        mot = mot.toLowerCase();
        
        for (String m : mots) {
            if (m.equals(mot)) {
                return 1;
            }
        }
        
        return 0;
    }


}
