package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.MaterialService.MaterialController;
import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.MaterialService.ServiceController;

import java.time.LocalDateTime;

public class ItemTuple {
    private int id;
    private LocalDateTime tansactionTime;

    public ItemTuple(int id, LocalDateTime tansactionTime) {
        this.id = id;
        this.tansactionTime = tansactionTime;
    }

    public int getId() {
        return id;
    }
    public LocalDateTime getTansactionTime() {
        return tansactionTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTansactionTime(LocalDateTime tansactionTime) {
        this.tansactionTime = tansactionTime;
    }

    public MaterialService getMaterialService() {
        if (MaterialController.getInstance().get(this.id) != null) {
            return MaterialController.getInstance().get(this.id);
        }else if (ServiceController.getInstance().get(this.id) != null) {
            return ServiceController.getInstance().get(this.id);
        }else{
            return null;
        }

    }
}
