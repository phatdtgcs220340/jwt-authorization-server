package com.phatdo.authorization_server.Entity.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Column(name = "full_name")
    private final String fullName;

    @Email
    @Column(name = "username", unique = true)
    private final String username;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(name = "password")
    private final String password;
}
