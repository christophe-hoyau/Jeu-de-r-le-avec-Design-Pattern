package eu.telecomnancy.labfx.utils;

import java.time.LocalDateTime;

public class Reservation {
    private LocalDateTime startRes;
    private LocalDateTime endRes;
    private int userId;

    public Reservation(LocalDateTime startRes, LocalDateTime endRes, int userId) {
        this.startRes = startRes;
        this.endRes = endRes;
        this.userId = userId;
    }

    public LocalDateTime getStartRes() {
        return startRes;
    }

    public void setStartRes(LocalDateTime startRes) {
        this.startRes = startRes;
    }

    public LocalDateTime getEndRes() {
        return endRes;
    }

    public void setEndRes(LocalDateTime endRes) {
        this.endRes = endRes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
