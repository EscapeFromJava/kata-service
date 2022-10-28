package com.example.authservice.services;

import com.example.authservice.models.dto.AccountResponseDto;
import com.example.authservice.models.dto.AuthResponseDto;
import com.example.authservice.models.dto.LoginRequestDto;
import com.example.authservice.models.dto.SignupRequestDto;
import com.example.authservice.models.entity.Account;
import com.example.authservice.models.enums.RoleNameEnum;
import com.example.authservice.repositories.AccountRepositories;
import com.example.authservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepositories accountRepositories;
    private final JwtUtil jwtUtil;

    @Autowired
    public AccountService(AccountRepositories accountRepositories, JwtUtil jwtUtil) {
        this.accountRepositories = accountRepositories;
        this.jwtUtil = jwtUtil;
    }

    public void saveAccount(SignupRequestDto signupRequestDto) {
        Account account = new Account();
        account.setEmail(signupRequestDto.getEmail());
        account.setPassword(signupRequestDto.getPassword());
        account.setRoleName(signupRequestDto.getRoleName());
        account.setFirstName(signupRequestDto.getFirstName());
        account.setLastName(signupRequestDto.getLastName());
        account.setBirthdayDate(signupRequestDto.getBirthdayDate());
        accountRepositories.save(account);
    }

    public AccountResponseDto getAccountById(Long id) {
        Account accountFromDB = accountRepositories.findById(id).get();
        AccountResponseDto account = new AccountResponseDto();
        account.setId(accountFromDB.getId());
        account.setEmail(accountFromDB.getEmail());
        account.setRole(RoleNameEnum.valueOf(accountFromDB.getRoleName()));
        account.setEnable(true);
        return account;
    }

    public AuthResponseDto getAuthResponseDtoWithToken(LoginRequestDto loginRequestDto) {
        Account account = accountRepositories.findAccountByEmail(loginRequestDto.getEmail());

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccountId(account.getId());
        authResponseDto.setEmail(account.getEmail());
        authResponseDto.setAccessToken(jwtUtil.generateAccessToken(account));
        return authResponseDto;
    }
}
