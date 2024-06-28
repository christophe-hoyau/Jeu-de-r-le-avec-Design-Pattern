package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.MaterialService.*;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryTest {
    @Test
    public void testCreateHistory() {
        // Arrange and Act
        ServiceController serviceController = ServiceController.getInstance();
        ArrayList<Service> services = serviceController.getServices();
        MaterialController materialController = MaterialController.getInstance();
        ArrayList<Material> materials = materialController.getMaterials();
        UserController userController = UserController.getInstance();
        ArrayList<User> users = userController.getUsers();
        User testUser = users.get(0);
        ArrayList<ItemTuple> itemsSell = testUser.getItemsSell();
        ArrayList<ItemTuple> itemsBuy = testUser.getItemsBuy();
        ArrayList<History> historyList = new ArrayList<>();

        for (ItemTuple itemTuple : itemsSell) {
            MaterialService materialService = itemTuple.getMaterialService();
            if (materialService != null) {
                historyList.add(new History(materialService, materialService.getCost(), itemTuple.getTansactionTime()));
            }
        }
        for (ItemTuple itemTuple : itemsBuy) {
            MaterialService materialService = itemTuple.getMaterialService();
            if (materialService != null) {
                historyList.add(new History(materialService, -materialService.getCost(), itemTuple.getTansactionTime()));
            }
        }
        historyList.forEach(p ->System.out.println(p.getMaterialService().getId() + " " + p.getTansactionTime() + " " + p.getPrice() + " " + p.getMaterialService().getType()));
        assertEquals(historyList.size(), 4);
        assertInstanceOf(Material.class, historyList.get(0).getMaterialService());
        assertInstanceOf(Service.class, historyList.get(1).getMaterialService());
        assertInstanceOf(Material.class, historyList.get(2).getMaterialService());
        assertInstanceOf(Service.class, historyList.get(3).getMaterialService());


        historyList = UserController.getInstance().sortHistory(historyList);
        assertEquals(historyList.size(), 4);
        System.out.println((historyList));




    }

}
