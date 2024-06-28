package eu.telecomnancy.labfx;

import eu.telecomnancy.labfx.MaterialService.*;
import eu.telecomnancy.labfx.Profil.EvaluationsController;
import eu.telecomnancy.labfx.Profil.HistoriqueRecentController;
import eu.telecomnancy.labfx.Profil.InfoPersoController;
import eu.telecomnancy.labfx.controller.AccueilController;
import eu.telecomnancy.labfx.controller.NavBarController;
import eu.telecomnancy.labfx.controller.PreviewItemController;
import eu.telecomnancy.labfx.controller.ResultatsController;
import eu.telecomnancy.labfx.controller.PageAnnonceController;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Redirection {

    public static void accueil(User user, Button actionButton){
        try {
            //On crée un contenair root qui sera une vbox
            VBox root = new VBox();
            root.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });

            root.getChildren().add(top.load());

            //On load le centre et on le met dans une anchorpane
            FXMLLoader center = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/accueil.fxml"));
            center.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            root.getChildren().add(center.load());

            //Creation d'une gridpane pour contenir les previewItems
            GridPane previews = new GridPane();
            previews.setAlignment(javafx.geometry.Pos.TOP_CENTER);
            //On recupere les 3 derniers services et les 3 derniers materials
            System.out.println("Size of materials: " + MaterialController.getInstance().getMaterials().size());
            ArrayList<Material> materials = new ArrayList(MaterialController.getInstance().sortByUpdateAt().subList(0, 2));
            ArrayList<Service> services = new ArrayList(ServiceController.getInstance().sortByUpdateAt().subList(0, 2));
            System.out.println(services.size() + " " + materials.size());
            //On ajoute les previewsItem dans la gridpane
            previews.setHgap(10); //horizontal gap in pixels
            previews.setVgap(10); //vertical gap in pixels
            for (int i = 0; i < materials.size(); i++) {
               FXMLLoader preview = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/previewItem.fxml"));
               Parent previewItem = preview.load();
               PreviewItemController previewItemController = preview.getController();
               previewItemController.setItem(materials.get(i), user);
               previews.add(previewItem, 0, i );
            }
            for (int i = 0; i < services.size(); i++) {
                FXMLLoader preview = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/previewItem.fxml"));
                Parent previewItem = preview.load();
                PreviewItemController previewItemController = preview.getController();
                previewItemController.setItem(services.get(i), user);
                previews.add(previewItem, 1, i );
            }
            root.getChildren().add(previews);



            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'accueil.");
        }
    }

    public static void recherche(User user, String resultMots, Button actionButton){
        try {
            //On crée un contenair root qui sera une vbox
            VBox root = new VBox();
            root.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });

            root.getChildren().add(top.load());

            VBox center = new VBox();
            center.setAlignment(Pos.TOP_CENTER);
            ResultatsController resultatsController = new ResultatsController();

            int [][]ListeService = resultatsController.initializeResultsService(resultMots);
            int [][]ListeMaterial = resultatsController.initializeResultsMateriel(resultMots);
            ArrayList<Material> materials = new ArrayList();
            ArrayList<Service> services = new ArrayList();
            for(int i=0; i<ListeMaterial.length; i++){
                materials.add((Material)MaterialController.getInstance().get(ListeMaterial[i][0]));
            }
            for (int i=0; i<ListeService.length; i++){
                services.add((Service)ServiceController.getInstance().get(ListeService[i][0]));
            }
            //Creation d'une gridpane pour contenir les previewItems
            VBox previews= new VBox();
            ScrollPane scrollpane = new ScrollPane(previews);


       
            previews.setAlignment(Pos.TOP_CENTER);

            //On recupere les 3 derniers services et les 3 derniers materials
            //On ajoute les previewsItem dans la gridpane
           
            for (int i = 0; i < materials.size(); i++) {
               FXMLLoader preview = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/previewItem.fxml"));
               Parent previewItem = preview.load();
               PreviewItemController previewItemController = preview.getController();
               previewItemController.setItem(materials.get(i), user);
               previews.getChildren().add(previewItem);
            }
            for (int i = 0; i < services.size(); i++) {
                FXMLLoader preview = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/previewItem.fxml"));
                Parent previewItem = preview.load();
                PreviewItemController previewItemController = preview.getController();
                previewItemController.setItem(services.get(i), user);
                previews.getChildren().add(previewItem);
            }
            root.getChildren().add(previews);



            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'accueil.");
        }
    }


    public static void goProfil(User user, Button actionButton) {
        try {
            // On charge la navbar
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            Parent navbar = top.load();

            // On charge la vue du profil
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/Profil/profil.fxml"));
            loader.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    } 
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            Parent profilRoot = loader.load();

            // On charge la vue de l'historique récent
            FXMLLoader historyLoader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/Profil/HistoriqueRecent.fxml"));
            historyLoader.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            Parent historyRoot = historyLoader.load();

            // On charge la vue des évaluations
            FXMLLoader evaluationsLoader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/Profil/Evaluations.fxml"));
            evaluationsLoader.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            Parent evaluationsRoot = evaluationsLoader.load();

            // On crée une VBox pour organiser les éléments verticalement
            VBox root = new VBox();
            root.getChildren().addAll(navbar, profilRoot);

            // Récupérer le contrôleur de l'historique récent et appeler initialize
            HistoriqueRecentController historyController = historyLoader.getController();
            historyController.initialize();

            // Récupérer le contrôleur des évaluations et appeler initialize
            EvaluationsController evaluationsController = evaluationsLoader.getController();
            evaluationsController.initialize();

            Scene profilScene = new Scene(root, 1920, 1080);
            Stage currentStage = (Stage) actionButton.getScene().getWindow();
            currentStage.setScene(profilScene);
            currentStage.setTitle("Profil");
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet Profil.");
        }
    }


    public static void inscription(ActionEvent event) {
        try {
            BorderPane root = new BorderPane();
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/inscription.fxml"));
            root.setCenter(loader.load());
            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'inscription.");
        }
    }

    public static void connexion(ActionEvent event) {
        try {
            BorderPane root = new BorderPane();
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/connexion.fxml"));
            root.setCenter(loader.load());
            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet de connexion.");
        }
    }

    public static void goAddItem (User user, Button actionButton) {
        try {
            VBox addItemRoot = new VBox();
            addItemRoot.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            addItemRoot.getChildren().add(top.load());

            //On load le formulaire
            FXMLLoader form = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/AddItem.fxml"));
            form.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            addItemRoot.getChildren().add(form.load());

            Scene scene = new Scene(addItemRoot, 1920, 1080);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'ajout d'item.");
        }
    }

    public static void pageAnnonce(User user, MaterialService item, Button actionButton){
        try {
            VBox root = new VBox();
            root.setAlignment(Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            root.getChildren().add(top.load());
            //On load la page de validation qui prend en parametre l'utilisateur et l'item dans son constructor
            FXMLLoader popUp = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/pageAnnonce.fxml"));
            popUp.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class, MaterialService.class);
                    if (cons != null) {
                        return cons.newInstance(user,item);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            root.getChildren().add(popUp.load());

            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'annonce.");
        }
    }
    // création de la page Résultats de recherche
    public static void searchFor(User user, MaterialService item, Button actionButton){
        try{
            VBox root = new VBox();
            root.setAlignment(Pos.TOP_CENTER);
            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            root.getChildren().add(top.load());
            FXMLLoader resultats = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/resultats.fxml"));
            resultats.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class, MaterialService.class);
                    if (cons != null) {
                        return cons.newInstance(user,item);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet resultats.");
        }
    }

    public static void popUpAnnonce(User user, MaterialService item, Button actionButton) {
        try {
            VBox root = new VBox();
            root.setAlignment(Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            root.getChildren().add(top.load());
            //On load la page de validation qui prend en parametre l'utilisateur et l'item dans son constructor
            FXMLLoader popUp = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/popUpReservation.fxml"));
            popUp.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class, MaterialService.class);
                    if (cons != null) {
                        return cons.newInstance(user,item);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            root.getChildren().add(popUp.load());

            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'annonce.");
        }
    }

    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
