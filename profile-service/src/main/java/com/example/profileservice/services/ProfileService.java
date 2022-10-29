package com.example.profileservice.services;

import com.example.profileservice.feign.AccountFeignClient;
import com.example.profileservice.models.dto.AccountResponseDto;
import com.example.profileservice.models.dto.ProfileCreateRequestDto;
import com.example.profileservice.models.dto.ProfileResponseDto;
import com.example.profileservice.models.dto.ProfileUpdateRequestDto;
import com.example.profileservice.models.entity.Profile;
import com.example.profileservice.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AccountFeignClient accountFeignClient;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, AccountFeignClient accountFeignClient) {
        this.profileRepository = profileRepository;
        this.accountFeignClient = accountFeignClient;
    }

    public void saveProfile(ProfileCreateRequestDto profileCreateRequestDto) {
        Profile profile = new Profile();
        profile.setAccountId(profileCreateRequestDto.getAccountId());
        profile.setFirstName(profileCreateRequestDto.getFirstName());
        profile.setLastName(profileCreateRequestDto.getLastName());
        profile.setBirthdayDate(profileCreateRequestDto.getBirthdayDate());
        profileRepository.save(profile);
    }

    public ProfileResponseDto getProfileByAccountId(Long accountId) {
        AccountResponseDto account = accountFeignClient.getAccountById(accountId).getBody();
        Profile profile = profileRepository.findByAccountId(accountId);

        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        profileResponseDto.setProfileId(profile.getId());
        profileResponseDto.setAccountId(account.getId());
        profileResponseDto.setFirstName(profile.getFirstName());
        profileResponseDto.setLastName(profile.getLastName());
        profileResponseDto.setEmail(account.getEmail());
        profileResponseDto.setBirthday(profile.getBirthdayDate());
        profileResponseDto.setRole(account.getRole());
        profileResponseDto.setEnable(account.getEnable());

        return profileResponseDto;
    }

    public void updateProfile(Long accountId, ProfileUpdateRequestDto profile) {
        Profile profileFromDB = profileRepository.findById(accountId).get();
        profileFromDB.setFirstName(profile.getFirstName());
        profileFromDB.setLastName(profile.getLastName());
        profileFromDB.setBirthdayDate(profile.getBirthday());
        profileRepository.save(profileFromDB);
    }

    public boolean isAdmin(Long accountId) {
        AccountResponseDto account = accountFeignClient.getAccountById(accountId).getBody();
        return account.getRole().name().equals("ADMIN");
    }
}
