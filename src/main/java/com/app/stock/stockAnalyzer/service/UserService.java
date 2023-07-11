package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.dto.UserDTO;
import com.app.stock.stockAnalyzer.entity.User;
import com.app.stock.stockAnalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j(topic = "UserServiceLog:")
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaProducer kafkaProducer;

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

            sendToTopicUserData(newUser);

        } else {
            throw new RuntimeException();
        }
    }

    public void sendToTopicUserData(User newUser) {
        kafkaProducer.sendKafkaMessage("Username: " + newUser.getUsername() +
                        ", " + "email: " + newUser.getEmail());
    }

    public UserDTO getUser(String name) {
        return convertToUserDTO(userRepository.getUserByUsername(name));
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
