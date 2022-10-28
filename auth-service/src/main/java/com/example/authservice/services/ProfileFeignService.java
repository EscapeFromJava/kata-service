package com.example.authservice.services;

import com.example.authservice.feign.ProfileFeignClient;
import com.example.authservice.models.dto.ProfileCreateRequestDto;
import com.example.authservice.models.dto.SignupRequestDto;
import com.example.authservice.models.entity.Account;
import com.example.authservice.repositories.AccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileFeignService {

    private final ProfileFeignClient profileFeignClient;
    private final AccountRepositories accountRepositories;

    @Autowired
    public ProfileFeignService(ProfileFeignClient profileFeignClient, AccountRepositories accountRepositories) {
        this.profileFeignClient = profileFeignClient;
        this.accountRepositories = accountRepositories;
    }

    public void saveProfile(SignupRequestDto signupRequestDto) {
        Account account = accountRepositories.findAccountByEmail(signupRequestDto.getEmail());

        ProfileCreateRequestDto profile = new ProfileCreateRequestDto();
        profile.setAccountId(account.getId());
        profile.setFirstName(signupRequestDto.getFirstName());
        profile.setLastName(signupRequestDto.getLastName());
        profile.setBirthdayDate(signupRequestDto.getBirthdayDate());
        profileFeignClient.saveProfile(profile);
    }
}
