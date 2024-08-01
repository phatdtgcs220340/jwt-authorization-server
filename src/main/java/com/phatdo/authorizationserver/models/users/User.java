package com.phatdo.authorizationserver.models.users;

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
    private int id;

    private final String fullName;

    @Column(unique = true)
    private final String username;

    private final String password;

    private String avatarUrl;

}
