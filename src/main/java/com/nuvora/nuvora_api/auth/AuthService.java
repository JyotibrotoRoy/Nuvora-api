package com.nuvora.nuvora_api.auth;

import com.nuvora.nuvora_api.user.User;
import com.nuvora.nuvora_api.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(UUID orgId, String email, String rawPassword) {

        User user = userRepository.findByOrgIdAndEmailAndIsActiveTrue(orgId, email)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        if(!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("Invalid Password");
        }

        return user;
    }
}
