package com.app.stock.stockAnalyzer.controller;

import com.app.stock.stockAnalyzer.dto.UserDTO;
import com.app.stock.stockAnalyzer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
//@RestController
//@RequestMapping("api/v1/login")
//@RequiredArgsConstructor
//public class AuthRestController {
//
//    private final UserService userService;
//
//    @GetMapping
//    public UserDTO user(@AuthenticationPrincipal OAuth2User principal) {
//        userService.save(principal);
//        return userService.getUser(principal.getAttribute("name"));
//    }
//}
