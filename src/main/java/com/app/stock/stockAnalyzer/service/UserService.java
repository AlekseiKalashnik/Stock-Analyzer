package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.dto.UserDTO;
import com.app.stock.stockAnalyzer.entity.UserEntity;
import com.app.stock.stockAnalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j(topic = "StockServiceLog:")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

//    public UserEntity findUserByEmail(String email) {
//        userRepository.
//    }

    public void save(UserDTO userDTO) {
        userRepository.save(convertToEntity(userDTO));
    }

    private UserEntity convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
