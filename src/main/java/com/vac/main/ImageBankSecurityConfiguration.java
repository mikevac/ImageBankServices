package com.vac.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class ImageBankSecurityConfiguration {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private IBUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
		httpSecurity
		    .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		    .authorizeHttpRequests(
						authorize -> authorize
						    .requestMatchers("/login").permitAll()
						    .requestMatchers("/js").permitAll()
						    .requestMatchers("/registration").permitAll()
						    .requestMatchers("/forgot").permitAll()
						    .anyRequest().authenticated()
		    )

		    .formLogin(form -> form
		            .loginPage("/login").permitAll()
		            .failureForwardUrl("/login?fail").permitAll()
		            .loginProcessingUrl("/login").permitAll()
		            .successForwardUrl("/secure/home")
		    )
		    .authenticationProvider(authenticationProvider());
		// @formatter:on
        return httpSecurity.build();
    }

}
