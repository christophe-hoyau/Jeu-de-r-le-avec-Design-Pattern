package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PreviewItemController {

    private MaterialService item;
    private User user;

    @FXML
    private Label cost;
    @FXML
    private Label description;
    @FXML
    private ImageView imgItem;
    @FXML
    private ToggleButton like;
    @FXML
    private Label name;
    @FXML
    private Label note;
    @FXML
    private Label title;
    @FXML
    private Label ville;
    @FXML
    private Button buy;

    @FXML
    void addFavori(ActionEvent event) {

    }
    public void initialize() {
        like.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                like.getStyleClass().add("liked");
            } else {
                like.getStyleClass().remove("liked");
            }
        });
       }

    public void setItem(MaterialService item, User user) {
        User owner = UserController.getInstance().getUserById(item.getOwner());
        this.user = user;
        this.item = item;
        this.title.setText(item.getName());
        this.description.setText(item.getDescription().replace("\\n", "\n"));
        this.name.setText(owner.getFirstName().concat(" ").concat(owner.getLastName()));
        this.cost.setText(String.valueOf(item.getCost()));
        this.note.setText(String.valueOf(owner.getAverageNote()));
        this.ville.setText(owner.getCity());
        try {
            String filePath = DirectoryHandler.getPathResources("/images/item/" + item.getImage());
            System.out.println("\t\t\t\t"+filePath);
            File file = new File(filePath);
            Image image = new Image(file.toURI().toString());

            this.imgItem.setImage(image);

        }catch (Exception e){
            try{
                System.out.println("image not found, loading base image");
                String filePath = PreviewItemController.class.getClass().getResource("/eu/telecomnancy/labfx/images/jaimeBien.png").getPath();
                System.out.println("\t\t\t\t\t\t\t"+filePath);
                File file = new File(filePath);
                Image image = new Image(file.toURI().toString());
                this.imgItem.setImage(image);
            }catch (Exception e2){
                System.out.println("Error while loading image");
            }

        }
    }

    @FXML
    void goPageAnnonce(ActionEvent event) {
        Redirection.pageAnnonce(this.user, this.item,  this.buy);
    }
}
