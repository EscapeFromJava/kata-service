package com.example.authservice.services;

import com.example.authservice.feign.ProfileFeignClient;
import com.example.authservice.models.dto.ProfileCreateRequestDto;
import com.example.authservice.models.dto.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileFeignService {

    private final ProfileFeignClient profileFeignClient;

    @Autowired
    public ProfileFeignService(ProfileFeignClient profileFeignClient) {
        this.profileFeignClient = profileFeignClient;
    }

    public void saveProfile(SignupRequestDto signupRequestDto) {
        ProfileCreateRequestDto profile = new ProfileCreateRequestDto();
        profile.setFirstName(signupRequestDto.getFirstName());
        profile.setLastName(signupRequestDto.getLastName());
        profile.setBirthdayDate(signupRequestDto.getBirthdayDate());
        profileFeignClient.saveProfile(profile);
    }
}
