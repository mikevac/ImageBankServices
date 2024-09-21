package com.vac.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    public IBCustomAuthenticationEntryPoint customEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        var delegate = new XorCsrfTokenRequestAttributeHandler();
        delegate.setCsrfRequestAttributeName("_csrf");

        CsrfTokenRequestHandler requestHandler = new CsrfTokenRequestHandler() {

            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                    Supplier<CsrfToken> csrfToken) {
                delegate.handle(request, response, csrfToken);

            }
        };
        httpSecurity
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> {
                    csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                    csrf.csrfTokenRequestHandler(requestHandler);
                })
                .authenticationProvider(authenticationProvider)
                .httpBasic(basic -> basic.authenticationEntryPoint(customEntryPoint).realmName("ImageBank"))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/config").permitAll();
                            auth.requestMatchers("/forgot").permitAll();
                            auth.requestMatchers("/registration").permitAll();
                            auth.requestMatchers("/index.html").permitAll();
                            auth.requestMatchers("/static/js/*").permitAll();
                            auth.requestMatchers("/static/css/*").permitAll();
                            auth.requestMatchers("/favicon.ico").permitAll();
                            auth.requestMatchers("/manifest.json").permitAll();
                            auth.requestMatchers("/logo192.png").permitAll();
                            auth.requestMatchers("/robots.txt").permitAll();
                            auth.requestMatchers("/").permitAll();
                            auth.anyRequest().authenticated();
                        });

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
