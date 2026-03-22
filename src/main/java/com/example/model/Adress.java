package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="adresses", schema="users")
@Entity
public class Adress {

    @Column
    private String street;
    @Column
    private String city;
    @Column(name="postal_code")
    private String postalCode;
    @Id
    @Column
    private int id;
    @Column(name="user_id")
    private int userId;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> users;

}
