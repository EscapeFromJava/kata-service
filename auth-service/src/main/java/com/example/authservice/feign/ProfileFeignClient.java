package com.example.authservice.feign;

import com.example.authservice.models.dto.ProfileCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service")
public interface ProfileFeignClient {
    @PostMapping("/api/inner/profile")
    ResponseEntity<String> saveProfile(@RequestBody ProfileCreateRequestDto profile);

}
