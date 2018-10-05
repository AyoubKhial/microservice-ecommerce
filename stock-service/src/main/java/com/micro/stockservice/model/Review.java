package com.micro.stockservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Review {
    private int id;
    private String title;
    private String description;
    private int rating;
    private Timestamp insertedAt;
    private Timestamp updatedAt;
    private Product product;
    private int userId;

    public Review() {
    }

    public Review(int id, String title, String description, int rating, Date insertedAt, Date updatedAt, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.insertedAt = new Timestamp(insertedAt.getTime());
        this.updatedAt = new Timestamp(updatedAt.getTime());
        this.userId = userId;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", columnDefinition = "TEXT")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "inserted_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Timestamp getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Timestamp insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Basic
    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
                rating == review.rating &&
                userId == review.userId &&
                Objects.equals(title, review.title) &&
                Objects.equals(description, review.description) &&
                Objects.equals(insertedAt, review.insertedAt) &&
                Objects.equals(updatedAt, review.updatedAt) &&
                Objects.equals(product, review.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, rating, insertedAt, updatedAt, product, userId);
    }
}
