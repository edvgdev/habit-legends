package com.habitlegends.habitlegends.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.habitlegends.habitlegends.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final CustomUserDetailsService customUserDetailsService;
        private final JwtAuthFilter jwtAuthFilter;

        public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthFilter jwtAuthFilter) {
                this.customUserDetailsService = customUserDetailsService;
                this.jwtAuthFilter = jwtAuthFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.csrf(AbstractHttpConfigurer::disable)
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests(request -> request
                                                .requestMatchers("/api/auth/**", "/api/public/**")
                                                .permitAll()
                                                .requestMatchers("/api/admin/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers("/api/user/**").hasAnyAuthority("USER")
                                                .requestMatchers("/api/adminuser/**").hasAnyAuthority("ADMIN", "USER")
                                                .anyRequest().authenticated())
                                .sessionManagement(manager -> manager
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider()).addFilterBefore(
                                                jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
                daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
                daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
                return daoAuthenticationProvider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }
}
