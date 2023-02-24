package com.testing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors().disable().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsManager() {
        UserDetails admin = User.withUsername("Rajasekhar").password(encoder().encode("raja")).roles("ADMIN").build();
        UserDetails user = User.withUsername("Sekhar").password(encoder().encode("sekhar")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
