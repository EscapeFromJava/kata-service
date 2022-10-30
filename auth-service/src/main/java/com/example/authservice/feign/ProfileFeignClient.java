package com.example.authservice.feign;

import com.example.authservice.models.dto.ProfileCreateRequestDto;
import com.example.authservice.models.dto.ProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profile-service")
public interface ProfileFeignClient {
    @PostMapping("/api/inner/profile")
    ResponseEntity<String> saveProfile(@RequestBody ProfileCreateRequestDto profile);

    @GetMapping("/api/inner/profile")
    ResponseEntity<ProfileResponseDto> getProfileByAccountId(@RequestParam("accountId") Long accountId);
}
