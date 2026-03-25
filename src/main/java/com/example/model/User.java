package com.example.model;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="users", schema="users")
public class User {

    @Id
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    // Указываем, что связь описана полем "user" в классе Address
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authority> authorities;
}
