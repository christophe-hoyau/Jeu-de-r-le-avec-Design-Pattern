package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.MaterialService.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonItemReaderWriterTest {
    @Test
    public void testGetItems() {
        // Arrange and Act
        ServiceController serviceController = ServiceController.getInstance();
        ArrayList<Service> services = serviceController.getServices();
        assertTrue(services.size()>=2);

        MaterialController materialController = MaterialController.getInstance();
        ArrayList<Material> materials = materialController.getMaterials();


    }

    @Test
    public void testSaveItems() {
        // Arrange and Act
        ServiceController serviceController = ServiceController.getInstance();
        ArrayList<Service> services = serviceController.getServices();

        MaterialController materialController = MaterialController.getInstance();
        ArrayList<Material> materials = materialController.getMaterials();
        assertEquals(materials.get(0).getOwner(), 1);
        System.out.println(materials.get(0).getOwner());
        int id = Math.max(materialController.getMaxId(), serviceController.getMaxId());
        for (int i = 0; i < 10; i++) {
            //create a new service
            Service service1 = new Service(id+1+i,"serviceTest".concat(String.valueOf(id+1+i)), 1, 0, "s", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), null, "service1.png", true);
            serviceController.add(service1);
        }
        int size  = serviceController.getServices().size();
        System.out.println(size);
        serviceController.saveItems();

        //create a new service
    }

    @Test
    public void testSortedByUpdateMaterials() {
        MaterialController materialController = MaterialController.getInstance();
        ArrayList<Material> materials = materialController.getMaterials();
        ArrayList<Material> materialsSorted = materialController.sortByUpdateAt();
        assertTrue(materialsSorted.get(0).getUpdatedAt().isAfter(materialsSorted.get(1).getUpdatedAt()));
        assertTrue(materialsSorted.get(1).getUpdatedAt().isAfter(materialsSorted.get(2).getUpdatedAt()));
    }

    @Test
    public void testSortedByUpdateServices() {
        ServiceController serviceController = ServiceController.getInstance();
        ArrayList<Service> services = serviceController.getServices();
        ArrayList<Service> servicesSorted = serviceController.sortByUpdateAt();
        assertTrue(servicesSorted.get(0).getUpdatedAt().isAfter(servicesSorted.get(1).getUpdatedAt()));
    }
}
