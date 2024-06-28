package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialController;
import eu.telecomnancy.labfx.MaterialService.ServiceController;
import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import eu.telecomnancy.labfx.utils.DirectoryHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddItemController {
    ObservableList<String> itemType = FXCollections.observableArrayList("Service", "Material");
    private User user;
    private String imagePath;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField endHour;
    @FXML
    private TextField endMinute;
    @FXML
    private TextField price;
    @FXML
    private ChoiceBox<String> selectType;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextField startHour;
    @FXML
    private TextField startMinute;
    @FXML
    private TextField title;
    @FXML
    private Button addImage;
    @FXML
    private ImageView currentImage;
    @FXML
    private Button validate;
    @FXML
    private Label errorText ;

    public AddItemController(User user) {
        this.user = user;
    }
    @FXML
    public void initialize() {
        selectType.setValue("Choisir un type");
    	selectType.setItems(itemType);
    }

    @FXML
    void validateOffer(ActionEvent event) {
        String type = selectType.getValue();
        String title = this.title.getText();
        int userID = this.user.getId();
        int price = Integer.parseInt(this.price.getText());
        String description = this.description.getText().replace("\n", "\\n");
        LocalDateTime startDate = toLocalDateTime(this.startDate.getValue(), this.startHour.getText(), this.startMinute.getText());
        LocalDateTime endDate = toLocalDateTime(this.endDate.getValue(), this.endHour.getText(), this.endMinute.getText());
        String imagePath = this.imagePath;
        if(type == null || title == null ||startDate == null || endDate == null){
            errorText.setText("Veuillez remplir tous les champs");
        }
        else{
            if(type.equals("Service")){
                ServiceController.getInstance().addServiceFromAttr(title, userID, price, description, startDate, endDate, imagePath);
            }
            else{
                MaterialController.getInstance().addMaterialFromAttr(title, userID, price, description, startDate, endDate, imagePath);
            }

            Redirection.accueil(this.user, this.validate);
        }
    }

    @FXML
    void addImage(ActionEvent event) {
        Stage stage = (Stage) addImage.getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            currentImage.setImage(new javafx.scene.image.Image(file.toURI().toString()));
            this.imagePath = file.getAbsolutePath();
        }
        ServiceController.getInstance().saveItems();

    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

    }

    private LocalDateTime toLocalDateTime(LocalDate date, String hour, String minute) {
        if(date == null){
            errorText.setText("La date ne peut pas être nulle");
            return null;
        }
        if(date.isBefore(LocalDate.now())){
            errorText.setText("La date ne peut pas être antérieure à la date actuelle");
            return null;
        }
        if(Integer.parseInt(hour)>23 || Integer.parseInt(hour)<0){
            errorText.setText("L'heure doit être comprise entre 0 et 23");
            return null;
        }
        if(Integer.parseInt(minute)>60 || Integer.parseInt(minute)<0){
            errorText.setText("Les minutes doivent être comprises entre 0 et 60");
            return null;
        }
        return LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
    }

}
