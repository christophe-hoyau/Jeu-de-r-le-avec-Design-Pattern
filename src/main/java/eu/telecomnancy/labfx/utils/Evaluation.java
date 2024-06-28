package eu.telecomnancy.labfx.utils;

import java.time.LocalDateTime;

public class Evaluation {
    private int id;
    private int idUser;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    public Evaluation(int id,int idUser, int rating, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.idUser = idUser;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
