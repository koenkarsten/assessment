package com.karsten.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()
        ).httpBasic().and().exceptionHandling().authenticationEntryPoint(httpTracing);

        return http.build();
    }

    // !!Explainer!! Including this to ensure endpoints are not publicly accessible by default, but want to flag that
    // this style of username and password management is better offloaded to a dedicated microservice. Implementing
    // advanced user management and security feels out of scope for the assessment at hand, but wanted to demonstrate
    // my knowledge on how to enforce authentication on API's.
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withUsername("john.doe@fakemail.com")
                        .password(passwordEncoder().encode("password"))
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
