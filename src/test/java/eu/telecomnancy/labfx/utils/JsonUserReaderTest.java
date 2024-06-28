package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.user.ClassicUser;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class JsonUserReaderTest {
    @Test
    public void testGetUsers() {
        // Arrange and Act

        UserController userController = UserController.getInstance();
        ArrayList<User> users = userController.getUsers();
        int maxId = userController.getMaxId();
        // Assert
        for (int i = 0; i < 10; i++) {
            assert users.size() <=150;
            assert users.get(0).getId() == 1;

            //create a new user
            ClassicUser user1 = new ClassicUser(maxId+1+i, "userTest".concat(String.valueOf(maxId+1+i)), "userTest".concat(String.valueOf(maxId+1+i)), "user1", "user1", "user@user.com", "Nancy", 0, LocalDateTime.of(2023,5,2,12,2,12), "user1.png", "user1", null,null, null, null, null);
            userController.addUser(user1);
            //userController.saveUsers();
        }
    }
}
