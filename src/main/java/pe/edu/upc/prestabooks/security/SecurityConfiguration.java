package pe.edu.upc.prestabooks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").authenticated()
            .antMatchers("/employees/**").hasRole("ADMIN")
            .antMatchers("/books/**").hasRole("ADMIN")
            .antMatchers("/readers/**").hasAnyRole("ADMIN", "EMPLOYEE")
            .antMatchers("/authors/**").hasRole("ADMIN")
            .antMatchers("/loans/**").hasAnyRole("ADMIN", "EMPLOYEE")
            .antMatchers("/reports/**").hasRole("ADMIN")
        .and()
        .formLogin()
            .loginProcessingUrl("/signin")
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/")
            .usernameParameter("username")
            .passwordParameter("password")
        .and()
            .exceptionHandling().accessDeniedPage("/access-denied")
        .and()
        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login");
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.myUserDetailService);

        return daoAuthenticationProvider;
    }
}
