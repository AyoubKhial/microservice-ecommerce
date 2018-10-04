package com.micro.stockservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Provider {
    private int id;
    private String name;
    private String email;

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
    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 150, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return id == provider.id &&
                Objects.equals(name, provider.name) &&
                Objects.equals(email, provider.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
