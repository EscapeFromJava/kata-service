package com.example.authservice.feign;

import com.example.authservice.models.dto.ProfileCreateRequestDto;
import com.example.authservice.models.dto.ProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service")
public interface ProfileFeignClient {
    @PostMapping("/api/inner/profile")
    ResponseEntity<String> saveProfile(@RequestBody ProfileCreateRequestDto profile);

    @GetMapping("/api/inner/profile/{accountId}")
    ResponseEntity<ProfileResponseDto> getProfileByAccountId(@PathVariable Long accountId);

}
