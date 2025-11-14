package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.User;
import com.entvy.openbidhub.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String email, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {

            log.warn("❌ 사용자 이메일 없음: " + email);
            return null;
        }

        User user = optionalUser.get();

        if (!user.isActive()) {
            log.warn("❌ 사용자 비활성화됨: " + email);
            return null;
        }

        boolean match = BCrypt.checkpw(rawPassword, user.getPassword());
        if (!match) {
            log.warn("❌ 비밀번호 불일치: " + email);
            return null;
        }

        log.info("✅ 로그인 성공: " + email);
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
