package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialController;
import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.MaterialService.MaterialServiceController;
import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PopUpController {
    private User user;
    private MaterialService item;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField endHour;
    @FXML
    private TextField endMinute;
    @FXML
    private Button reserve;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextField startHour;
    @FXML
    private TextField startMinute;
    @FXML
    private Label errorText;

    public PopUpController(User user, MaterialService item) {
        this.user = user;
        this.item = item;
    }

    @FXML
    void reserve(ActionEvent event) {
        LocalDateTime startDate = toLocalDateTime(this.startDate.getValue(), this.startHour.getText(), this.startMinute.getText());
        LocalDateTime endDate = toLocalDateTime(this.endDate.getValue(), this.endHour.getText(), this.endMinute.getText());

        Reservation reservation = new Reservation(startDate, endDate, this.user.getId());
        item.addReservation(reservation);
        MaterialController.getInstance().saveItems();
        user.removeFlorains(item.getCost());
        UserController.getInstance().getUserById(item.getOwner()).addFlorains(item.getCost());
        Redirection.accueil(this.user, reserve);
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
