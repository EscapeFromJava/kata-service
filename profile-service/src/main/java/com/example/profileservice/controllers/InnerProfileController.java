package com.example.profileservice.controllers;

import com.example.profileservice.models.dto.ProfileCreateRequestDto;
import com.example.profileservice.models.dto.ProfileResponseDto;
import com.example.profileservice.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inner/profile")
public class InnerProfileController {

    private final ProfileService profileService;

    @Autowired
    public InnerProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<String> saveProfile(@RequestBody ProfileCreateRequestDto profile) {
        profileService.saveProfile(profile);
        return new ResponseEntity<>("Profile saved successfully", HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<ProfileResponseDto> getProfileByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(profileService.getProfileByAccountId(accountId), HttpStatus.OK);
    }

}
