package com.phatdo.authorizationserver.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phatdo.authorizationserver.models.users.User;
import com.phatdo.authorizationserver.models.users.UserRepository;
import com.phatdo.authorizationserver.exception.CustomError;
import com.phatdo.authorizationserver.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new CustomUserDetails(user);
    }

    public void saveUser(String fullName, String username, String password) throws CustomException {
        log.info("Save user: {}", username);
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            throw new CustomException(CustomError.USER_EXIST);
        }
        userRepository.save(new User(fullName, username, passwordEncoder.encode(password)));

    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
