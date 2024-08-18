package com.vac.main;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

    @Value("${cors.origins}")
    private String allowedOrigins;

    @Autowired
    public IBAuthenticationProvider authenticationProvider;

    @Autowired
    private IBCustomAuthenticationEntryPoint customEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
        
		httpSecurity
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		    .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		    .httpBasic(basic -> basic.authenticationEntryPoint(customEntryPoint).realmName("ImageBank"))
		    .authenticationProvider(authenticationProvider)
		    .authorizeHttpRequests(
						authorize -> authorize
						    .requestMatchers("/").permitAll()
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
						    .requestMatchers("robots.txt").permitAll()
						    .anyRequest().authenticated()
						    
		    );
		    
		// @formatter:on
        return httpSecurity.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                String[] origins = allowedOrigins.split(",");
                var ccfg = new CorsConfiguration();
                ccfg.setAllowedOrigins(Arrays.asList(origins));
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
