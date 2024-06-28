package eu.telecomnancy.labfx.user;

import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.utils.*;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonUserReader;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonUserWritter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class UserController {
    //use singleton pattern to create only one instance of the controller whitch contains the user list ArrayList<User>
    private static UserController instance = null;
    private ArrayList<User> users;
    //singleton pattern
    private UserController() {
        System.out.println("Reading users from json: " + DirectoryHandler.getPathResources("/data/user.json"));
        this.users = JsonUserReader.read(DirectoryHandler.getPathResources("/data/user.json"));

    }

/*    public void initialread(){
        this.users = JsonUserReader.read(UserController.class.getResource("/eu/telecomnancy/labfx/data/user.json").getPath());
    }*/
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
            System.out.println("Creating user controller");
        }
        return instance;
    }
    //getters
    public ArrayList<User> getUsers() {
        return users;
    }
    public void addUser(User user) {
        users.add(user);
    }

    public void saveUsers() {
        JsonUserWritter.write(DirectoryHandler.getPathResources("/data/user.json"), this.users);
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public User getUserByIdentifiant(String identifiant) {
        for (User user : users) {
            if (user.getIdentifiant().equals(identifiant)) {
                return user;
            }
        }
        return null;
    }
    public User testConnexion(String identifiant, String password) {
        User user = getUserByIdentifiant(identifiant);
        if (user == null) {
            return null;
        } else if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }


    }

    public int getMaxId() {
        int maxId = 0;
        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId;
    }

    public void createClassicUser(String identifiant, String password, String firstName, String lastName, String email, String city, int florains, String image, String description) {
        int id = getMaxId()+1;
        User user = new ClassicUser(id, identifiant, password, firstName, lastName, email, city, florains, LocalDateTime.now(), image, description, new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Evaluation>() ,new ArrayList<ItemTuple>(), new ArrayList<ItemTuple>());
        users.add(user);
        saveUsers();
    }


    public ArrayList<History> getRecentHistory(User user) {
        ArrayList<ItemTuple> itemsSell = user.getItemsSell();
        ArrayList<ItemTuple> itemsBuy = user.getItemsBuy();
        HashSet<Integer> addedItems = new HashSet<>(); // Utiliser un ensemble pour éviter les doublons
        ArrayList<History> history = new ArrayList<>();

        for (ItemTuple itemTuple : itemsSell) {
            System.out.println(2);
            MaterialService materialService = itemTuple.getMaterialService();
            if (materialService != null && addedItems.add(materialService.getId())) {
                history.add(new History(materialService, materialService.getCost(), itemTuple.getTansactionTime()));
        }
        }
    
        for (ItemTuple itemTuple : itemsBuy) {
            System.out.println(3);
            MaterialService materialService = itemTuple.getMaterialService();
            if (materialService != null && addedItems.add(materialService.getId())) {
                history.add(new History(materialService, -materialService.getCost(), itemTuple.getTansactionTime()));
            }
        }
        return sortHistory(history);
    
    }

    public ArrayList<History> sortHistory(ArrayList<History> historyToSort){
        ArrayList<History> sortedHistory = new ArrayList<>(historyToSort);
        sortedHistory.sort((h1, h2) -> h2.getTansactionTime().compareTo(h1.getTansactionTime()));
        return sortedHistory;
    }

    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);
                saveUsers(); // Appeler saveUsers après la mise à jour
                break;
            }
        }
    }

    public ArrayList<Evaluation> getEvaluationsSorted(User user) {
        ArrayList<Evaluation> evaluations = user.getEvaluations();
        Collections.sort(evaluations, (e1, e2) -> e2.getCreatedAt().compareTo(e1.getCreatedAt()));
        return evaluations;
    }

}

