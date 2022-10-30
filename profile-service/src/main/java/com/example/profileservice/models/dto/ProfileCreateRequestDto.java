package com.example.profileservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCreateRequestDto {
    private Long accountId;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;
}
