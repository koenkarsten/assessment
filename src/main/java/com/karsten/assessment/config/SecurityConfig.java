package com.karsten.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final HttpTracing httpTracing;

    public SecurityConfig(HttpTracing httpTracing) {
        this.httpTracing = httpTracing;
    }

    // Enforce authentication and request tracing.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()
        ).httpBasic().and().exceptionHandling().authenticationEntryPoint(httpTracing);

        return http.build();
    }

    // Allow OpenAPI and Swagger access without authentication.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui/**");
    }

    // !!Explainer!! Including this to ensure endpoints are not publicly accessible by default, but want to flag that
    // this style of username and password management is better offloaded to a dedicated microservice. Implementing
    // advanced user management and security feels out of scope for the assessment at hand, but wanted to demonstrate
    // some knowledge on how to enforce authentication on API's but would not submit this for production workloads.
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withUsername("johndoe")
                        .password(passwordEncoder().encode("7PTZ15Xxx2fN0saGRLt68TeevpdaywyY"))
                        .roles("ADMIN")
                        .build()
        );

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
