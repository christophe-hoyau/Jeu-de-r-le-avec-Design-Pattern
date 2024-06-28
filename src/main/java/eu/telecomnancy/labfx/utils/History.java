package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.MaterialService.MaterialService;

import java.time.LocalDateTime;

public class History {
    private MaterialService materialService;
    private int price;
    private LocalDateTime tansactionTime;
    public History(MaterialService materialService, int price, LocalDateTime tansactionTime) {
        this.materialService = materialService;
        this.price = price;
        this.tansactionTime = tansactionTime;
    }

    public MaterialService getMaterialService() {
        return materialService;
    }

    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getTansactionTime() {
        return tansactionTime;
    }

    public void setTansactionTime(LocalDateTime tansactionTime) {
        this.tansactionTime = tansactionTime;
    }
}
