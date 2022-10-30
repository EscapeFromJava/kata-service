package com.example.authservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @Email
    @NonNull
    private String email;
    @NonNull
    private String password;
}
