package com.mehbub.neo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/* Main class for spring security */

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index.html", "/css/*, /js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /* 
        purpose : How we are retrived users from db
    */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        
        UserDetails polashUser =  User.builder()
            .username("polash")
            .password( passwordEncoder.encode("password") )
            .roles(ApplicationUserRole.CUSTOMER.name()) //Spring will treat this as ROLE_Customer
            .build();

        UserDetails adminUser = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("password"))
            .roles(ApplicationUserRole.ADMIN.name())
            .build();

        return new InMemoryUserDetailsManager (
            polashUser,adminUser
        );
    }

    

}
