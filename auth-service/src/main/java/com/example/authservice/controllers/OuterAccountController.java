package com.example.authservice.controllers;

import com.example.authservice.models.dto.LoginRequestDto;
import com.example.authservice.models.dto.SignupRequestDto;
import com.example.authservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class OuterAccountController {
    private final AccountService accountService;
    @Autowired
    public OuterAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> saveAccount(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        accountService.saveAccount(signupRequestDto);
        return new ResponseEntity<>("Account saved successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authUserWithGenerateToken(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        if (!accountService.correctPassword(loginRequestDto)) {
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountService.register(loginRequestDto), HttpStatus.OK);
    }
}
