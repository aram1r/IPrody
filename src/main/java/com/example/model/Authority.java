package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="authorities", schema="users")
@Entity
public class Authority {
    @Id
    @Column
    private long id;
    @Column(name="user_id")
    private int userId;
    @Column(name="authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
