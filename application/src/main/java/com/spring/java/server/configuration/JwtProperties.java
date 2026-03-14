package com.spring.java.server.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

@Configuration
@ConfigurationProperties(prefix = "app.jwt")
@Getter
@Setter
public class JwtProperties {

    private String secret;
    private long accessTokenExpirationMinutes;
    private long refreshTokenExpirationDays;
    private boolean rotateRefreshTokens;
    private boolean blacklistAfterRotation;
    private String headerPrefix;
}