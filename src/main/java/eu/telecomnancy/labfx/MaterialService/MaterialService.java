package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MaterialService {
    public void activate();
    public void deactivate();
    public void addReservation(Reservation reservation);
    public void removeReservation(Reservation reservation);

    public int getId();
    public String getName();
    public String getType();
    public int getOwner();
    public int getCost();
    public String getDescription();
    public LocalDateTime getCreatedAt();
    public LocalDateTime getUpdatedAt();
    public LocalDateTime getStartTime();
    public LocalDateTime getEndTime();
    public ArrayList<Reservation> getReservationDelays();
    public String getImage();
    public boolean isActive();

    public void setId(int id);
    public void setName(String name);
    public void setType(String type);
    public void setOwner(int owner);
    public void setCost(int cost);
    public void setDescription(String description);
    public void setCreatedAt(LocalDateTime createdAt);
    public void setUpdatedAt(LocalDateTime updatedAt);
    public void setStartTime(LocalDateTime startTime);
    public void setEndTime(LocalDateTime endTime);
    public void setReservationDelays(ArrayList<Reservation> reservations);
    public void setImage(String image);
    public void setActive(boolean active);

}
