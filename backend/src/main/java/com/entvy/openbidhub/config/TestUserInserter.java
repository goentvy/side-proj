package com.entvy.openbidhub.config;

import com.entvy.openbidhub.domain.User;
import com.entvy.openbidhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("local")
public class TestUserInserter implements CommandLineRunner {

    private final UserRepository userRepository;

    public TestUserInserter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        List<User> testUsers = List.of(
                createUser("admin@test.com", "ADMIN"),
                createUser("user1@test.com", "USER"),
                createUser("user2@test.com", "USER")
        );

        for (User user : testUsers) {
            if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                userRepository.save(user);
                System.out.println("✅ 삽입 완료: " + user.getEmail());
            } else {
                System.out.println("ℹ️ 이미 존재함: " + user.getEmail());
            }
        }
    }

    private User createUser(String email, String role) {
        String hashedPassword = BCrypt.hashpw("1234", BCrypt.gensalt());
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setRole(role);
        user.setActive(true);
        return user;
    }
}