package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.entity.User;
import com.app.stock.stockAnalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j(topic = "StockServiceLog:")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void save(OAuth2User principal) {
        String username = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        User existUser = userRepository.getUserByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setCreatedAt(LocalDateTime.now());
            userRepository.save(newUser);
        } else {
            throw new RuntimeException();
        }
    }
}
