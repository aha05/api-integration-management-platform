package com.apibridge.integration.user.service;

import com.apibridge.integration.common.SecurityRules;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers(HttpMethod.GET, "/user").hasAuthority("USER_READ")
                .requestMatchers(HttpMethod.POST, "/user").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/user/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("ADMIN");
    }
}
