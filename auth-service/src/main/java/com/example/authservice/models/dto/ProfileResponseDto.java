package com.example.authservice.models.dto;

import com.example.authservice.models.enums.RoleNameEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileResponseDto {
    private Long profileId;
    private Long accountId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    private RoleNameEnum role;
    private Boolean enable;
}
