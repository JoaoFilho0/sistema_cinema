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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling((exceptionHandling) -> {
            exceptionHandling.authenticationEntryPoint(authenticationEntryPoint());
        });
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/usuario").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/cinema").hasAnyRole("USER", "MOVIETHEATER", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/cinema/sala").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.POST, "/cinema/horario").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.POST, "/cinema/filme").hasAnyRole("MOVIETHEATER", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/cinema/sessao").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.GET, "/cinema").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/{id}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/sala/{id}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/horario/{id}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/filme").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/cinema/sessao").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/usuario").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/usuario").hasAnyRole("USER", "MOVIETHEATER", "ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/cinema").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.PUT, "/cinema/horario").hasRole("MOVIETHEATER");
                    req.requestMatchers(HttpMethod.PUT, "/cinema/filme").hasAnyRole("MOVIETHEATER", "ADMIN");
                    req.requestMatchers(HttpMethod.DELETE, "/usuario").hasAnyRole("USER", "ADMIN", "MOVIETHEATER");
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
