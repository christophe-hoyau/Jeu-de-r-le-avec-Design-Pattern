package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Service implements MaterialService{
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

    public Service(int id,String name, int owner, int cost, String description, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startTime, LocalDateTime endTime, ArrayList<Reservation> reservations, String image, boolean isActive) {
        this.id = id;
        this.name = name;
        this.type = "service";
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Reservation> getReservationDelays() {
        return reservations;
    }

    public void setReservationDelays(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

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
