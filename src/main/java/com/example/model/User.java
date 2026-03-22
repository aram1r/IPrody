package com.example.model;

import lombok.*;
import jakarta.persistence.*;

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
    private int id;
    @Column
    private String name;
    @Column
    private String email;

    @OneToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;
}
