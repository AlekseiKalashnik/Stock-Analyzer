package com.app.stock.stockAnalyzer.controller;

import com.app.stock.stockAnalyzer.dto.UserDTO;
import com.app.stock.stockAnalyzer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/login")
public class AuthRestController {
    private final UserService userService;
//    private final EmailService emailService;

    @GetMapping
    public UserDTO enterOrSaveUser(@AuthenticationPrincipal OAuth2User principal) {
        userService.save(principal);
        return userService.getUser(principal.getAttribute("name"));
    }

//    @GetMapping("/{user-email}")
//    public ResponseEntity<String> sendSimpleEmail(@PathVariable("user-email") String email) {
//        emailService.sendSimpleEmail(email, "Welcome to Stock-Analyzer", "This is for you!");
//        log.info("email was send");
//        return new ResponseEntity<>("Please check your email inbox", HttpStatus.OK);
//    }
}
