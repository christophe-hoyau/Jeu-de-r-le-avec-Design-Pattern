package eu.telecomnancy.labfx.user;

import eu.telecomnancy.labfx.utils.Evaluation;
import eu.telecomnancy.labfx.utils.EvaluationController;
import eu.telecomnancy.labfx.utils.ItemTuple;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AdminUser implements User{
    int id;
    String identifiant;
    String password;
    String firstName;
    String lastName;
    String email;
    String city;
    String role;
    int florains;
    LocalDateTime createdAt;
    String image;
    String description;
    ArrayList<Integer> itemsOwned;
    ArrayList<Integer> favouriteItems;
    ArrayList<Evaluation> evaluations;
    ArrayList<ItemTuple> itemsSell;
    ArrayList<ItemTuple> itemsBuy;

    public AdminUser(int id, String identifiant, String password, String firstName, String lastName, String email, String city, int florains, LocalDateTime createdAt, String image, String description, ArrayList<Integer> itemsOwned, ArrayList<Integer> favouriteItems, ArrayList<Evaluation> evaluations , ArrayList<ItemTuple> itemsSell, ArrayList<ItemTuple> itemsBuy) {
        this.id = id;
        this.identifiant = identifiant;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.role = "admin";
        this.florains = florains;
        this.createdAt = createdAt;
        this.image = image;
        this.description = description;
        this.itemsOwned = itemsOwned;
        this.favouriteItems = favouriteItems;
        this.evaluations = evaluations;
        this.itemsSell = itemsSell;
        this.itemsBuy = itemsBuy;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getIdentifiant() {
        return identifiant;
    }

    @Override
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int getFlorains() {
        return florains;
    }

    @Override
    public void setFlorains(int florains) {
        this.florains = florains;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ArrayList<Integer> getItemsOwned() {
        return itemsOwned;
    }

    @Override
    public void setItemsOwned(ArrayList<Integer> itemsOwned) {
        this.itemsOwned = itemsOwned;
    }

    @Override
    public ArrayList<ItemTuple> getItemsSell() {
        return itemsSell;
    }

    @Override
    public void setItemsSell(ArrayList<ItemTuple> itemsSell) {
        this.itemsSell = itemsSell;
    }

    @Override
    public ArrayList<ItemTuple> getItemsBuy() {
        return itemsBuy;
    }

    @Override
    public void setItemsBuy(ArrayList<ItemTuple> itemsBuy) {
        this.itemsBuy = itemsBuy;
    }

    @Override
    public ArrayList<Integer> getFavouriteItems() {
        return favouriteItems;
    }

    @Override
    public void setFavouriteItems(ArrayList<Integer> favouriteItems) {
        this.favouriteItems = favouriteItems;
    }

    @Override
    public ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }

    @Override
    public void setEvaluations(ArrayList<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public void addItemOwned(int id) {
        itemsOwned.add(id);
    }

    @Override
    public void addItemSell(ItemTuple itemTuple) {
        itemsSell.add(itemTuple);
    }

    @Override
    public void addItemBuy(ItemTuple itemTuple) {
        itemsBuy.add(itemTuple);
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

    @Override
    public void addFavouriteItem(int id) {
        favouriteItems.add(id);
    }

    @Override
    public void removeItemOwned(int id) {
        itemsOwned.remove(id);
    }

    @Override
    public void removeItemSell(int id) {
        itemsSell.remove(id);
    }

    @Override
    public void removeItemBuy(int id) {
        itemsBuy.remove(id);
    }

    @Override
    public void removeEvaluation(int id) {
        evaluations.remove(id);
    }

    @Override
    public void removeFavouriteItem(int id) {
        favouriteItems.remove(id);
    }

    @Override
    public void removeFlorains(int amount) {
        florains -= amount;
    }

    @Override
    public void addFlorains(int amount) {
        florains += amount;
    }

    @Override
    public void addEvaluation(int note, String comment, int idUserOfComment) {
        Evaluation eval = new Evaluation(this.getEvaluations().size()+1, idUserOfComment, note, comment, LocalDateTime.now());
        evaluations.add(eval);
    }

    @Override
    public double getAverageNote() {
        int sum = 0;
        for (Evaluation evaluation : evaluations) {
            sum += evaluation.getRating();
        }
        return (double)Math.round((sum / (double) evaluations.size()) * 10) / 10;
    }

    @Override
    public String getShortenDescription() {
        if (description.length() > 300) {
            return description.substring(0, 300) + "...";
        }
        return description;
    }
}
