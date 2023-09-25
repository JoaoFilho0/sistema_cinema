package com.system.movietheater.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/usuario").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/cinema").hasRole("USER");
                    req.requestMatchers(HttpMethod.POST, "/cinema").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.POST, "/cinema/sala").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.POST, "/cinema/horario").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.POST, "/cinema/filme").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.POST, "/cinema/filme").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/cinema/sessao").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.POST, "/cinema").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.GET, "/cinema").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/sala/{id}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/horario/{id}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/filme").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/sessao").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/usuario").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/usuario").hasRole("USER");
                    req.requestMatchers(HttpMethod.PUT, "/usuario").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/usuario").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.PUT, "/cinema").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.PUT, "/cinema/horario").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.PUT, "/cinema/filme").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.DELETE, "/usuario").hasRole("USER");
                    req.requestMatchers(HttpMethod.DELETE, "/usuario").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.DELETE, "/usuario").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.DELETE, "/cinema/horario/{id}").hasRole("MOVIETHEATER");
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
