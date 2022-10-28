package com.example.authservice.models.dto;

import com.example.authservice.models.enums.RoleNameEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
    private Long id;
    private String email;
    private RoleNameEnum role;
    private Boolean enable;
}
