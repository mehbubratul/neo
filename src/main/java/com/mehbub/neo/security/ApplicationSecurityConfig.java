package com.mehbub.neo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/* Main class for spring security */

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeRequests(
                (authz) -> authz
                    .antMatchers("index")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .httpBasic();
        http
            .authorizeRequests()
            .antMatchers("index", "/css/*, /js/*")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
        return http.build();
    }*/

   
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

}
