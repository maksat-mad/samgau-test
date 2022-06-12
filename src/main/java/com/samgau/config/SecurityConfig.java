package com.samgau.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.samgau.security.UserRole.LIBRARIAN;
import static com.samgau.security.UserRole.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index",
                        "/authors", "/books", "/genres",
                        "/searchAuthor", "/searchBook", "/searchGenre").permitAll()
                .antMatchers("/profile").hasRole(STUDENT.name())
                .antMatchers("/profile/**").hasAnyRole(STUDENT.name(), LIBRARIAN.name())
                .antMatchers("/update/**", "/remove-one-book/**", "/increase-book/**",
                        "/remove-book/**", "/addAuthor/**", "/addBook/**", "/addGenre/**",
                        "/updateGenre/**", "/remove-genre/**",
                        "/updateAuthor/**", "/remove-author/**",
                        "/borrows/**", "/analysis/**", "/analysisAuthor/**",
                        "/analysisBook/**", "/analysisGenre/**").hasRole(LIBRARIAN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails romaUser = User.builder()
                .username("roma")
                .password(passwordEncoder.encode("123"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails ayanUser = User.builder()
                .username("ayan")
                .password(passwordEncoder.encode("123"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails maksatUser = User.builder()
                .username("maksat")
                .password(passwordEncoder.encode("123"))
                .authorities(LIBRARIAN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                romaUser,
                ayanUser,
                maksatUser
        );

    }
}
