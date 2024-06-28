package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.Counter;
import eu.telecomnancy.labfx.utils.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Material implements MaterialService{
    private int id;
    private String name;
    private String type;
    private int owner;
    private int cost;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ArrayList<Reservation> reservations;
    private String image;
    private boolean isActive;

    public Material(int id, String name, int owner, int cost, String description, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startTime, LocalDateTime endTime, ArrayList<Reservation> reservations, String image, boolean isActive) {
        this.id = id;
        this.name = name;
        this.type = "materiel";
        this.owner = owner;
        this.cost = cost;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservations = reservations;
        this.image = image;
        this.isActive = isActive;
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
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public int getOwner() {
        return owner;
    }
    @Override
    public void setOwner(int owner) {
        this.owner = owner;
    }
    @Override
    public int getCost() {
        return cost;
    }
    @Override
    public void setCost(int cost) {
        this.cost = cost;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }
    @Override
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }
    @Override
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    @Override
    public ArrayList<Reservation> getReservationDelays() {
        return reservations;
    }
    @Override
    public void setReservationDelays(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
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
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void activate() {
        isActive = true;
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }
}
