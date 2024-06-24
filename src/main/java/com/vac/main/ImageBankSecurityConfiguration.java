package com.vac.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class ImageBankSecurityConfiguration {

    @Autowired
    public DaoAuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
		httpSecurity
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		    .authorizeHttpRequests(
						authorize -> authorize
						    .requestMatchers("/").permitAll()
						    .requestMatchers("/login").permitAll()
						    .requestMatchers("/js").permitAll()
						    .requestMatchers("/registration").permitAll()
						    .requestMatchers("/forgot").permitAll()
						    .anyRequest().authenticated()
		    )

//		    .formLogin(form -> form
//		            .
//		            .loginPage("/login").permitAll()
//		            .failureForwardUrl("/login?fail").permitAll()
//		            .loginProcessingUrl("/login").permitAll()
//		            .successForwardUrl("/secure/home")
//		    )
		    .authenticationProvider(authenticationProvider);
		// @formatter:on
        return httpSecurity.build();
    }

}
