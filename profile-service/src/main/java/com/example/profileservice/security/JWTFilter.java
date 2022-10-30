package com.example.profileservice.security;

import com.example.profileservice.feign.AccountFeignClient;
import com.example.profileservice.models.dto.AccountResponseDto;
import com.example.profileservice.models.enums.RoleNameEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final AccountFeignClient accountFeignClient;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, AccountFeignClient accountFeignClient) {
        this.jwtUtil = jwtUtil;
        this.accountFeignClient = accountFeignClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
            } else {
                Long id = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                AccountResponseDto account = accountFeignClient.getAccountById(id).getBody();

//                для API PUT /api/profile
                Long requestAccountId = Long.parseLong(request.getHeader("accountId"));

//                для API GET /api/profile
//                Long requestAccountId = Long.parseLong(request.getParameter("accountId"));
                if (id.equals(requestAccountId) && account.getRole().name().equals(RoleNameEnum.USER.name())) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setHeader("error", "Access denied");
                    response.setStatus(403);
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", "Access denied");
                    response.setContentType("application/json");
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/api/inner/profile", request.getServletPath());
    }
}
