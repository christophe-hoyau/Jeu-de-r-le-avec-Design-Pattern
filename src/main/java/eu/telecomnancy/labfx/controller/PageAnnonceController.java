package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class PageAnnonceController {

    private User user;
    private MaterialService item;

    @FXML
    private Label city;
    @FXML
    private Label date;
    @FXML
    private Text description;
    @FXML
    private ImageView imageAnnonce;
    @FXML
    private Label name;
    @FXML
    private Label note;
    @FXML
    private Label price;
    @FXML
    private Button reserve;
    @FXML
    private Label titre;

    public PageAnnonceController(User user, MaterialService item){
        this.user = user;
        this.item = item;
    }

    @FXML
    void initialize() {
        User owner = UserController.getInstance().getUserById(item.getOwner());
        titre.setText(item.getName());
        this.name.setText(owner.getFirstName().concat(" ").concat(owner.getLastName()));
        city.setText(owner.getCity());
        description.setText(item.getDescription().replace("\\n", "\n"));
        note.setText(String.valueOf(owner.getAverageNote()));
        price.setText(String.valueOf(item.getCost()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date.setText(item.getStartTime().format(formatter)+" à "+item.getEndTime().format(formatter));
        try {
            String filePath = DirectoryHandler.getPathResources("/images/item/" + item.getImage());
            System.out.println("\t\t\t\t"+filePath);
            File file = new File(filePath);
            Image image = new Image(file.toURI().toString());

            this.imageAnnonce.setImage(image);

        }catch (Exception e) {
            try {
                System.out.println("image not found, loading base image");
                String filePath = PreviewItemController.class.getClass().getResource("/eu/telecomnancy/labfx/images/jaimeBien.png").getPath();
                System.out.println("\t\t\t\t\t\t\t" + filePath);
                File file = new File(filePath);
                Image image = new Image(file.toURI().toString());
                this.imageAnnonce.setImage(image);
            } catch (Exception e2) {
                System.out.println("Error while loading image");
            }
        }
    }

    @FXML
    void reserve(ActionEvent event) {
        System.out.println("Dans l'appel apres le bouton " + this.user.getIdentifiant());
        Redirection.popUpAnnonce(this.user, this.item, this.reserve);
    }

}
