package com.example.authservice.services;

import com.example.authservice.feign.ProfileFeignClient;
import com.example.authservice.models.dto.*;
import com.example.authservice.models.entity.Account;
import com.example.authservice.models.enums.RoleNameEnum;
import com.example.authservice.repositories.AccountRepository;
import com.example.authservice.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ProfileFeignClient profileFeignClient;
    private final JWTUtil jwtUtil;

    @Autowired
    public AccountService(AccountRepository accountRepository, ProfileFeignClient profileFeignClient, JWTUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.profileFeignClient = profileFeignClient;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public void saveAccount(SignupRequestDto signupRequestDto) {
        Account account = new Account();
        account.setEmail(signupRequestDto.getEmail());
        account.setPassword(signupRequestDto.getPassword());
        account.setRoleName(signupRequestDto.getRoleName());
        account.setEnable(true);
        accountRepository.save(account);

        ProfileCreateRequestDto profile = new ProfileCreateRequestDto();
        profile.setAccountId(account.getId());
        profile.setFirstName(signupRequestDto.getFirstName());
        profile.setLastName(signupRequestDto.getLastName());
        profile.setBirthdayDate(signupRequestDto.getBirthdayDate());
        profileFeignClient.saveProfile(profile);
    }

    public AccountResponseDto getAccountById(Long id) {
        Account accountFromDB = accountRepository.findById(id).get();
        AccountResponseDto account = new AccountResponseDto();
        account.setId(accountFromDB.getId());
        account.setEmail(accountFromDB.getEmail());
        account.setRole(RoleNameEnum.valueOf(accountFromDB.getRoleName()));
        account.setEnable(accountFromDB.getEnable());
        return account;
    }

    public AuthResponseDto register(LoginRequestDto loginRequestDto) {
        Account account = accountRepository.findAccountByEmail(loginRequestDto.getEmail());

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccountId(account.getId());
        authResponseDto.setEmail(account.getEmail());
        authResponseDto.setAccessToken(jwtUtil.generateAccessToken(account));
        return authResponseDto;
    }

    public Boolean correctPassword(LoginRequestDto loginRequestDto) {
        Account account = accountRepository.findAccountByEmail(loginRequestDto.getEmail());
        return loginRequestDto.getPassword().equals(account.getPassword());
    }
}
