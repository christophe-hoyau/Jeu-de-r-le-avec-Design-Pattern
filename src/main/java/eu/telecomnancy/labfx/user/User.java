package eu.telecomnancy.labfx.user;

import eu.telecomnancy.labfx.utils.Evaluation;
import eu.telecomnancy.labfx.utils.ItemTuple;

import java.time.LocalDateTime;
import java.util.ArrayList;


public interface User {
    public int getId();
    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getIdentifiant();
    public String getEmail();
    public String getCity();
    public String getRole();
    public int getFlorains();
    public LocalDateTime getCreatedAt();
    public String getImage();
    public String getDescription();
    public ArrayList<Integer> getItemsOwned();
    public ArrayList<ItemTuple> getItemsSell();
    public ArrayList<ItemTuple> getItemsBuy();


    public void setFirstName(String name);
    public void setLastName(String lastName);
    public void setPassword(String password);
    public void setId(int id);
    public void setIdentifiant(String identifiant);
    public void setEmail(String email);
    public void setCity(String city);
    public void setRole(String role);
    public void setFlorains(int florains);
    public void setCreatedAt(LocalDateTime createdAt);
    public void setImage(String image);
    public void setDescription(String description);
    public void setItemsOwned(ArrayList<Integer> itemsOwned);
    public void setItemsSell(ArrayList<ItemTuple> itemsSell);
    public void setItemsBuy(ArrayList<ItemTuple> itemsBuy);
    public ArrayList<Evaluation> getEvaluations();
    public ArrayList<Integer> getFavouriteItems();
    public void setFavouriteItems(ArrayList<Integer> favouriteItems);
    public void setEvaluations(ArrayList<Evaluation> evaluations);
    public void addItemOwned(int id);
    public void addItemSell(ItemTuple itemTuple);
    public void addItemBuy(ItemTuple itemTuple);
    public void addEvaluation(Evaluation evaluation);
    public void addFavouriteItem(int id);
    public void removeItemOwned(int id);
    public void removeItemSell(int id);
    public void removeItemBuy(int id);
    public void removeEvaluation(int id);
    public void removeFavouriteItem(int id);
    public void removeFlorains(int florains);
    public void addFlorains(int florains);
    public void addEvaluation(int note, String comment, int idUserOfComment);
    public double getAverageNote();
    public String getShortenDescription();
}
