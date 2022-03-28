package com.example.pdp_meal.config.security;

import com.example.pdp_meal.config.security.filters.CustomAuthenticationFilter;
import com.example.pdp_meal.config.security.filters.CustomAuthorizationFilter;
import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.entity.AuthUser;
import com.example.pdp_meal.enums.State;
import com.example.pdp_meal.enums.Status;
import com.example.pdp_meal.mapper.auth.AuthUserMapper;
import com.example.pdp_meal.repository.AuthUserRepository;
import com.example.pdp_meal.service.auth.AuthUserService;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        jsr250Enabled = true
)
@Getter
@Setter
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final AuthUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository repository;

    public SecurityConfigurer(AuthUserService userService, PasswordEncoder passwordEncoder, AuthUserMapper authUserMapper, AuthUserRepository repository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authUserMapper = authUserMapper;
        this.repository = repository;
    }


//    @PostConstruct
//    public void init() {
//        List<AuthUser> authUserList = Collections.emptyList();
//        authUserList = repository.findAll();
//        if (authUserList.isEmpty()) {
//            AuthUser authUser = AuthUser.builder()
//                    .username("admin")
//                    .fullName("Najmiddin")
//                    .password(passwordEncoder.encode("123"))
//                    .chatId("x")
//                    .department("admin")
//                    .phone("1111111")
//                    .position("super_admin")
//                    .role("super_admin")
//                    .active(true)
//                    .state(State.REGISTERED)
//                    .build();
//            repository.save(authUser);
//        }
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/swagger-resources/**",
         "/swagger-ui.html",
         "/v2/api-docs",
         "/webjars/**", "/api/login/**", "/api/token/refresh/**")
                .permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

