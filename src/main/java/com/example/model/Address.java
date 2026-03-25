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
@Table(name="addresses", schema="users")
@Entity
public class Address {

    @Id
    @Column
    private int id;
    @Column
    private String street;
    @Column
    private String city;
    @Column(name="postal_code")
    private String postalCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
