package com.example.profileservice.controllers;

import com.example.profileservice.models.dto.ProfileResponseDto;
import com.example.profileservice.models.dto.ProfileUpdateRequestDto;
import com.example.profileservice.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class OuterProfileController {

    private final ProfileService profileService;

    @Autowired
    public OuterProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestHeader("accountId") Long accountId, @RequestBody ProfileUpdateRequestDto profile) {
        profileService.updateProfile(accountId, profile);
        return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<ProfileResponseDto> getProfileById(@PathVariable Long accountId) {
        return new ResponseEntity<>(profileService.getProfileByAccountId(accountId), HttpStatus.OK);
    }
}
