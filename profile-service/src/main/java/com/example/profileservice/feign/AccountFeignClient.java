package com.example.profileservice.feign;

import com.example.profileservice.models.dto.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AccountFeignClient {

    @GetMapping("/api/internal/account/{id}")
    ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long id);
}
