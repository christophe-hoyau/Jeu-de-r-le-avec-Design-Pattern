package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.MaterialService.Material;
import eu.telecomnancy.labfx.MaterialService.MaterialController;
import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.MaterialService.MaterialServiceController;
import eu.telecomnancy.labfx.MaterialService.Service;
import eu.telecomnancy.labfx.MaterialService.ServiceController;
import eu.telecomnancy.labfx.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class MesPretsServicesController {

    @FXML
    private ListView<String> materialListView;

    @FXML
    private ListView<String> serviceListView;

    private User user;

    public MesPretsServicesController(User user) {
        this.user = user;
    }

    public void initialize() {
    ArrayList<Material> materials = new ArrayList<Material>();
    ArrayList<Service> services = new ArrayList<Service>();
    for (Integer x : user.getItemsOwned()) {
        Material material = (Material) MaterialController.getInstance().get(x);
        Service service = (Service) ServiceController.getInstance().get(x);
        if(material != null)
            materials.add(material);
        else if(services != null)
            services.add(service);
    }
    }
}
