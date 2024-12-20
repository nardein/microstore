package com.microstore.configuration;

/*
import com.microstore.filter.JwtAuthorizationFilter;
import com.microstore.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabilita CSRF per API RESTful
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/users/delete").hasAnyRole("admin")
                                .requestMatchers("/auth/login", "/users/register").permitAll()// Endpoint di autenticazione pubbli
                        //.anyRequest().hasAnyRole("ciao")// Tutti gli altri richiedono autenticazione
                )

                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class); // Filtro per autorizzare JWT

        return http.build();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabilita CSRF per API RESTful
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // Endpoint di autenticazione pubblici
                       // .anyRequest().authenticated() // Tutti gli altri richiedono autenticazione
                )

                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class); // Filtro per autorizzare JWT

        return http.build();
    }*/

/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}*/
