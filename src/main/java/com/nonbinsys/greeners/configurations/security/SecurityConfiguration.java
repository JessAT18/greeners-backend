/*
package com.nonbinsys.greeners.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    protected InMemoryUserDetailsManager configureAuthentication() {
*/
/*        auth.inMemoryAuthentication()
                .withUser("employee")
                .password("abcasdada")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("adsadasd")
                .roles("ADMIN");*//*

        List<UserDetails> userDetailsList = new ArrayList<>();
        List<GrantedAuthority> userRoles = new ArrayList<>();
        userRoles.add(new SimpleGrantedAuthority("Administrador Comercio"));


        List<GrantedAuthority> adRoles = new ArrayList<>();
        userRoles.add(new SimpleGrantedAuthority("Administrador Emprendimiento"));

        userDetailsList.add(new User("JessAT18", "Passw0rd", userRoles));
        userDetailsList.add(new User("Sarech", "Passw0rd", adRoles));

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
*/
/*        http
            .authorizeHttpRequests((authz) -> authz
                    .anyRequest().authenticated()
            )
                .httpBasic(withDefaults());*//*

        //Example 1
        http.authorizeRequests()
                .antMatchers("/api/comercios/listarComercios").hasRole("Administrador Emprendimiento")
                .antMatchers("/api/comercios/*").hasAnyRole("Administrador Emprendimiento", "Administrador comercio")
                .antMatchers("/api/comercios/*").permitAll()
                .and().formLogin();
        return http.build();

        //Exmaple 2

    }
}

*/
