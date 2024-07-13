package com.vac.main;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
@ComponentScan("com.vac.main")
public class ImageBankSecurityConfiguration {

    @Autowired
    public IBAuthenticationProvider authenticationProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        //@formatter:off
        AuthenticationManagerBuilder authManBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authManBuilder.authenticationProvider(authenticationProvider);
        var authMan = authManBuilder.build();
        return authMan;
        //@formatter:on
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
		httpSecurity
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		    //.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
		    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		    .csrf(csrf-> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		    .authorizeHttpRequests(
						authorize -> authorize
						    .requestMatchers("/").permitAll()
						    .requestMatchers("/login").permitAll()
						    .requestMatchers("/config").permitAll()
                            .requestMatchers("/forgot").permitAll()
                            .requestMatchers("/registration").permitAll()
						    .requestMatchers("index.html").permitAll()
						    .requestMatchers("hostfile.json").permitAll()
						    .requestMatchers("static/*").permitAll()
						    .requestMatchers("static/js/*").permitAll()
						    .requestMatchers("static/css/*").permitAll()
						    .requestMatchers("favicon.ico").permitAll()
						    .requestMatchers("manifest.json").permitAll()
						    .requestMatchers("logo192.png").permitAll()
						    .anyRequest().authenticated()
		    );
		// @formatter:on
        var chain = httpSecurity.build();
        return chain;
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                var ccfg = new CorsConfiguration();
                ccfg.setAllowedOrigins(Arrays.asList("https://localhost:3000"));
                ccfg.setAllowedMethods(Collections.singletonList("*"));
                ccfg.setAllowCredentials(true);
                ccfg.setAllowedHeaders(Collections.singletonList("*"));
                ccfg.setExposedHeaders(Arrays.asList("Authorization"));
                ccfg.setMaxAge(3600L);
                return ccfg;

            }
        };
    }
}
