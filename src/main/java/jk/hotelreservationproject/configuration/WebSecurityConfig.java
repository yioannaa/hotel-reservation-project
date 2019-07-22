package jk.hotelreservationproject.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final
    DataSource dataSource;

    final
    PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/about", "/contact").hasAnyAuthority("ROLE_CLIENT", "ROLE_ADMIN")
                .antMatchers("/about").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
        .and().csrf().disable()
        .formLogin().loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
//                .failureUrl("/errorLogin")
                .defaultSuccessUrl("/")
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.email, u.password, true FROM user u WHERE u.email = ?")
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name FROM " +
                        "user u JOIN role r " +
                        "ON (u.role_id = r.id) " +
                        "WHERE u.email = ?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);

    }


}
