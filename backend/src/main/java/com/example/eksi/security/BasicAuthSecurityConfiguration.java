package com.example.eksi.security;

import com.example.eksi.exceptions.UnauthorizedException;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.eksi.security.jwt.TokenFilter;
import com.example.eksi.security.jwt.JwtUtils;
import com.example.eksi.security.services.UserDetailsServiceImpl;
import com.example.eksi.repositories.UserRepository;

@Configuration
public class BasicAuthSecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;

    private final HandlerExceptionResolver exceptionResolver;

    public BasicAuthSecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                                          @Qualifier("handlerExceptionResolver")
                                          HandlerExceptionResolver exceptionResolver) {
        this.userDetailsService = userDetailsService;
        this.exceptionResolver = exceptionResolver;
    }

    @Bean
    TokenFilter authenticationJwtTokenFilter(JwtUtils jwtUtils, UserRepository userRepository) {
        return new TokenFilter(jwtUtils, userRepository);
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, TokenFilter tokenFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers("/api/auth/login", "/api/auth/signup", "/api/auth/refresh").permitAll()
                .requestMatchers(HttpMethod.GET, "/uploads/**").permitAll()
                .requestMatchers("/api/search/**").permitAll()
                .requestMatchers("/api/users/*/entries").permitAll()
                .requestMatchers("/api/users/*/favorites").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/tags").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/topics/*/tags").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/topics/debe").permitAll()
                .requestMatchers("/api/topics/popular").permitAll()
                .requestMatchers("/api/topics/id/**").permitAll()
                .requestMatchers("/api/topics/tag/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/entries/**").permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(e -> e
            .authenticationEntryPoint((req, res, ex) ->
                exceptionResolver.resolveException(
                    req,
                    res,
                    null,
                    new UnauthorizedException("Authentication is required")))
            .accessDeniedHandler((req, res, ex) ->
                exceptionResolver.resolveException(req, res, null, ex))
        );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.applyPermitDefaultValues();
            corsConfig.addAllowedOrigin("http://localhost:3000");
            return corsConfig;
        }));

        return http.build();
    }

}
