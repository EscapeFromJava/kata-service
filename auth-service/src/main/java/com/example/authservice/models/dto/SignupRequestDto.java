package com.example.authservice.models.dto;

import com.example.authservice.models.validation.CheckRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    @Email
    private String email;
    private String password;
    @CheckRole
    private String roleName;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;
}
