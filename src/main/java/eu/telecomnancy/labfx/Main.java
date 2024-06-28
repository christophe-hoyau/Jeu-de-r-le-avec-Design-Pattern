package eu.telecomnancy.labfx;


import eu.telecomnancy.labfx.MaterialService.MaterialController;
import eu.telecomnancy.labfx.MaterialService.ServiceController;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DirectoryHandler.setPathHead();
        if (!new File(DirectoryHandler.getPathHead()).exists()) {
            DirectoryHandler.generateFolder();
            System.out.println("Resources copied successfully to: " + DirectoryHandler.getPathHead());
        }
        UserController.getInstance();
        ServiceController.getInstance();
        System.out.println("test ses grands morts" + MaterialController.getInstance().getMaterials().size());

        primaryStage.setTitle("TelecomNancy DirectDealing");
        UserController userController = UserController.getInstance();
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/connexion.fxml"));
        root.setCenter(loader.load());
        Scene scene = new Scene(root,1920,1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

