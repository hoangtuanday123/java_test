package com.example.demo_java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo_java.User.CustomUserDetails;
import com.example.demo_java.User.UserService;
import com.example.demo_java.jwt.JwtAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
    UserService userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
		http
            .csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/login","/").permitAll()
                .requestMatchers("/random").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
                
			)
            .formLogin(form -> form.disable())
			.logout((logout) -> logout.permitAll());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
    // Custom Authentication Manager bean
    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    }
}