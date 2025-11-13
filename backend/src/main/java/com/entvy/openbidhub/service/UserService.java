package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.User;
import com.entvy.openbidhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String email, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            System.out.println("❌ 사용자 이메일 없음: " + email);
            return null;
        }

        User user = optionalUser.get();

        if (!user.isActive()) {
            System.out.println("❌ 사용자 비활성화됨: " + email);
            return null;
        }

        boolean match = BCrypt.checkpw(rawPassword, user.getPassword());
        if (!match) {
            System.out.println("❌ 비밀번호 불일치: " + email);
            return null;
        }

        System.out.println("✅ 로그인 성공: " + email);
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
