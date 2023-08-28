package com.vac.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ImageBankSecurityConfiguration {

    @Autowired
    private IBUserDetailsService ibUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/js").permitAll()
                                .requestMatchers("/registration").permitAll()
                                .requestMatchers("/forgot").permitAll()
                                .anyRequest().authenticated()
                ).userDetailsService(ibUserDetailsService);
        return httpSecurity.build();
    }

}
