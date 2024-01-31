package com.citygusa.citygusatech.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                 .antMatchers("/login").permitAll()
                 .antMatchers("/register").permitAll()
                 .anyRequest().authenticated()
                 .and()
                 .formLogin().loginPage("/login").permitAll()
                 .and()
                 .logout().permitAll();
            return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        // Mudança: usar AuthenticationManagerBuilder diretamente
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(web);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return builder.build();
    }


}
