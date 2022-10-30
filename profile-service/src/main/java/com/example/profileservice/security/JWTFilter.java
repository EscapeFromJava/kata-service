package com.example.profileservice.security;

import com.example.profileservice.feign.AccountFeignClient;
import com.example.profileservice.models.dto.AccountResponseDto;
import com.example.profileservice.models.enums.RoleNameEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token in Bearer Header");
            } else {
                Long accountTokenId = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                AccountResponseDto account = accountFeignClient.getAccountById(accountTokenId).getBody();
                if (account.getRole().name().equals(RoleNameEnum.USER.name()) && account.getEnable()) {
                    try {
                        Long headerId = Long.parseLong(request.getHeader("accountId"));
                        if (accountTokenId.equals(headerId)) {
                            filterChain.doFilter(request, response);
                        } else {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                        }
                    } catch (NumberFormatException e) {
                        try {
                            Long parameterId = Long.parseLong(request.getParameter("accountId"));
                            if (accountTokenId.equals(parameterId)) {
                                filterChain.doFilter(request, response);
                            } else {
                                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                            }
                        } catch (NumberFormatException | IOException | ServletException ex) {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                        }
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token in Bearer Header");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/api/inner/profile", request.getServletPath());
    }


}
