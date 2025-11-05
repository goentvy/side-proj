package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.User;
import com.entvy.openbidhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String email, String password) {
        User user = findByEmail(email);
        if (user == null) return null;
        if (!BCrypt.checkpw(password, user.getPassword())) return null;
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
