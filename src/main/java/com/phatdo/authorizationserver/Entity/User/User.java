package com.phatdo.authorizationserver.Entity.User;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "\"user\"")
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "full_name")
    private final String fullName;

    @Column(name = "username", unique = true)
    private final String username;

    @Column(name = "password")
    private final String password;
}
