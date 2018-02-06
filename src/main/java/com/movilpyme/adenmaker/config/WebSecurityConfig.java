package com.movilpyme.adenmaker.config;

import com.movilpyme.adenmaker.security.AuthenticationFailureHandler;
import com.movilpyme.adenmaker.security.AuthenticationSuccessHandler;
import com.movilpyme.adenmaker.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
        return new TokenAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        //                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
//                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
        /*        .antMatchers(HttpMethod.GET, "/product/**").permitAll()
                .antMatchers(HttpMethod.GET, "/group/**").permitAll()
                .antMatchers("/cart/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll() */

                .anyRequest().authenticated()
//                .anyRequest().hasRole("admin") << Works with ROLE entities while we have SimpleGrantedAuthority...
                .anyRequest().hasAuthority("admin")

//               .httpBasic().disable();
                .and().formLogin().successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)

                // From https://github.com/bfwg/springboot-jwt-starter
                .and().csrf().disable();
    }
}
