package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.DirectoryHandler;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemReader;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemWritter;
import eu.telecomnancy.labfx.utils.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServiceController implements MaterialServiceController{
    private ArrayList<Service> services = null;
    private static ServiceController instance = null;

    private ServiceController() {
        this.services = read(DirectoryHandler.getPathResources("/data/item.json"));
    }

    public static ServiceController getInstance() {
        if (instance == null) {
            instance = new ServiceController();
            System.out.println("Creating service controller");
        }
        return instance;
    }


    public void add(Service service) {
        this.services.add(service);
    }

    public void addServiceFromAttr(String title, int userId , int price , String description, LocalDateTime startTime, LocalDateTime endTime, String imagePath) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        int id = Math.max(this.getMaxId()+1, MaterialController.getInstance().getMaxId()+1);
        Service service = new Service(id, title, userId, price ,description, createdAt, updatedAt,startTime, endTime, reservations, imagePath, true);
        DirectoryHandler.saveNewServiceImage(service, imagePath);
        this.services.add(service);
        saveItems();
    }

    @Override
    public MaterialService get(int id) {
        for (Service service : this.services) {
            if (service.getId() == id) {
                return service;
            }
        }
        return null;
    }

    public ArrayList<Service> read(String resourcePath) {
        ArrayList<MaterialService> materialsOrServices = JsonItemReader.read(resourcePath);
        ArrayList<Service> services1 = new ArrayList<Service>();
        for (MaterialService materialOrService : materialsOrServices) {
            if (materialOrService instanceof Service) {
                services1.add((Service) materialOrService);
            }
        }
        return services1;
    }

    public void saveItems() {
        System.out.println("Writing items in json: " + DirectoryHandler.getPathResources("/data/item.json"));
        JsonItemWritter.write(DirectoryHandler.getPathResources("/data/item.json"), this.services, MaterialController.getInstance().getMaterials());
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public int getMaxId() {
        int maxId = 0;
        for (Service service : this.services) {
            if (service.getId() > maxId) {
                maxId = service.getId();
            }
        }
        return maxId;
    }

    public ArrayList<Service> sortByUpdateAt(){
        ArrayList<Service> sortedServices = new ArrayList<Service>();
        for (Service service : this.services) {
            sortedServices.add(service);
        }
        sortedServices.sort((o1, o2) -> o2.getUpdatedAt().compareTo(o1.getUpdatedAt()));
        return sortedServices;
    }
}
