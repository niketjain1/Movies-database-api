package com.Project.Movie.security;

import com.Project.Movie.security.jwt.JwtAuthenticationConverter;
import com.Project.Movie.security.jwt.JwtAuthenticationFilter;
import com.Project.Movie.security.jwt.JwtAuthenticationManager;
import com.Project.Movie.security.jwt.*;
import com.Project.Movie.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class AppSecurityConfiguration {
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfiguration(
            JwtService jwtService,
            UserService userService
    ) {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(
                new JwtAuthenticationManager(
                        jwtService, userService
                )
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // TODO: in prod, cors and csrf shouldn't be blanket disabled
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers("/about").permitAll()
//                .antMatchers("/movies").permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
                .antMatchers("/*/**").authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
