package com.example.profileservice.initializer;

import com.example.profileservice.models.dto.ProfileCreateRequestDto;
import com.example.profileservice.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Component
public class ProfileGenerator implements CommandLineRunner {

    private final ProfileService profileService;

    @Autowired
    public ProfileGenerator(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void run(String... args) throws Exception {
        profileService.saveProfile(new ProfileCreateRequestDto("Ivan", "Ivanov", LocalDate.now()));
        profileService.saveProfile(new ProfileCreateRequestDto("Oleg", "Smirnov", LocalDate.now().minusYears(1)));
        profileService.saveProfile(new ProfileCreateRequestDto("Irina", "Aleksandrova", LocalDate.now().minusYears(2)));
    }
}
