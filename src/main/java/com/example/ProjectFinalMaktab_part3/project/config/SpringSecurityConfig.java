package com.example.ProjectFinalMaktab_part3.project.config;


import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;


import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public SpringSecurityConfig( UserServiceImpl userService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/login","/registrationUser/**").permitAll()
           .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/customer/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers("/specialist/**").hasAnyAuthority("SPECIALIST", "ADMIN")
                .antMatchers("/tasks/**").hasAnyAuthority("SPECIALIST", "ADMIN")
                .antMatchers("/subtask/**").hasAnyAuthority("SPECIALIST", "ADMIN")
                .antMatchers("/orders/**").permitAll()
                .antMatchers("/offers/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").usernameParameter("email");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService );
    }


}
