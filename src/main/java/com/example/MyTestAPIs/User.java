package com.example.MyTestAPIs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {

    public User() {
    }

    public User(String user_name, Integer user_age, String country) {
        this.user_name = user_name;
        this.user_age = user_age;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 45)
    private String user_name;

    @Column(length = 45)
    private Integer user_age;
    @Column(length = 45)
    private String country;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", user_age=" + user_age +
                ", country='" + country + '\'' +
                '}';
    }
}