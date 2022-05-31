package com.mehbub.neo.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        // configureSecurityUsingAntMatchers(http);
        configureSecurityUsingAntMatchers2(http);
    }
    /* 
        purpose : How we are retrived users from db
    */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        
        // UserDetails customerUser = buildUserDetailsUsingRole("polash","password",ApplicationUserRole.CUSTOMER.name());
        // UserDetails adminUser = buildUserDetailsUsingRole("ratul","password",ApplicationUserRole.ADMIN.name());
        // UserDetails adminTraineeUser = buildUserDetailsUsingRole("sakif","password",ApplicationUserRole.ADMIN_TRAINEE.name());

        UserDetails customerUser = buildUserDetailsUsingAuthorities("polash","password",ApplicationUserRole.CUSTOMER.getGrantedAuthorities());
        UserDetails adminUser = buildUserDetailsUsingAuthorities("ratul","password",ApplicationUserRole.ADMIN.getGrantedAuthorities());
        UserDetails adminTraineeUser = buildUserDetailsUsingAuthorities("sakif","password",ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities());

        return new InMemoryUserDetailsManager (
            customerUser,adminUser,adminTraineeUser
        );
    }

    private void configureSecurityUsingAntMatchers2(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //TO DO : Need to activate later.
            .authorizeRequests()
            .antMatchers("/","/index.html", "/css/*, /js/*").permitAll() 
            .antMatchers("/api/**").hasRole(ApplicationUserRole.CUSTOMER.name()) 
            .antMatchers(HttpMethod.POST, "/management/api/**") .hasAuthority(ApplicationUserPermission.CUSTOMER_WRITE.getPermission())
            .antMatchers(HttpMethod.PUT, "/management/api/**") .hasAuthority(ApplicationUserPermission.CUSTOMER_WRITE.getPermission())
            .antMatchers(HttpMethod.DELETE, "/management/api/**") .hasAuthority(ApplicationUserPermission.CUSTOMER_WRITE.getPermission())
            .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMIN_TRAINEE.name())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    private UserDetails buildUserDetailsUsingAuthorities(String userName, String pwd,
            Set<SimpleGrantedAuthority> grantedAuthorities) {
        UserDetails tempUser = User.builder()
                .username(userName)
                .password(passwordEncoder.encode(pwd))
                .authorities(grantedAuthorities)
                .build();

        return tempUser;
    }

    private UserDetails buildUserDetailsUsingRole(String userName, String pwd, String userRole) {
        UserDetails tempUser = User.builder()
            .username(userName)
            .password(passwordEncoder.encode(pwd))
            .roles(userRole)
            .build();
        return tempUser;
    }

    private void configureSecurityUsingAntMatchers(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //TO DO : Need to activate later.
                .authorizeRequests()
                .antMatchers("/","/index.html", "/css/*, /js/*")
                    .permitAll()
                .antMatchers("/api/**")
                    .hasRole(ApplicationUserRole.CUSTOMER.name())
                // .antMatchers("/management/api/**")
                //     .hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMIN_TRAINEE.name())   
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}
