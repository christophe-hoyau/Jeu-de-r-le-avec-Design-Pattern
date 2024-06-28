package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.DirectoryHandler;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemReader;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemWritter;
import eu.telecomnancy.labfx.utils.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MaterialController implements MaterialServiceController{
    private ArrayList<Material> materials = null;
    private  static MaterialController instance = null;

    private MaterialController() {
        this.materials = read(DirectoryHandler.getPathResources("/data/item.json"));
    }

    public static MaterialController getInstance() {
        if (instance == null) {
            instance = new MaterialController();
            System.out.println("Creating material controller");
        }
        return instance;
    }


    public void add(Material material) {
        this.materials.add(material);
    }

    public void addMaterialFromAttr(String title, int userId , int price , String description, LocalDateTime startTime, LocalDateTime endTime, String imagePath) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        int id =Math.max(this.getMaxId()+1, ServiceController.getInstance().getMaxId()+1);
        Material material = new Material(id, title, userId, price ,description, createdAt, updatedAt,startTime, endTime, reservations, imagePath, true);
        DirectoryHandler.saveNewMaterialImage(material, imagePath);
        this.materials.add(material);
        saveItems();
    }
    @Override
    public MaterialService get(int id) {
        for (Material material : this.materials) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }


    public ArrayList<Material> read(String resourcePath) {
        ArrayList<MaterialService> materialsOrServices = JsonItemReader.read(resourcePath);
        ArrayList<Material> materials1 = new ArrayList<Material>();
        for (MaterialService materialOrService : materialsOrServices) {
            if (materialOrService instanceof Material) {
                materials1.add((Material) materialOrService);
            }
        }
        return materials1;
    }

    public void saveItems() {
        System.out.println("Writing items in json: " + DirectoryHandler.getPathResources("/data/item.json"));
        JsonItemWritter.write(DirectoryHandler.getPathResources("/data/item.json"), ServiceController.getInstance().getServices(), this.materials);
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public int getMaxId() {
        int maxId = 0;
        for (Material material : this.materials) {
            if (material.getId() > maxId) {
                maxId = material.getId();
            }
        }
        return maxId;
    }

    public ArrayList<Material> sortByUpdateAt(){
        ArrayList<Material> sortedMaterials = new ArrayList<Material>(this.materials);
        sortedMaterials.sort((o1, o2) -> o2.getUpdatedAt().compareTo(o1.getUpdatedAt()));
        return sortedMaterials;
    }
}
