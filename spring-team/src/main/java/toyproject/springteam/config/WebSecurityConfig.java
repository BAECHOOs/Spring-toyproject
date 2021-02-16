package toyproject.springteam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/account/loginTest").hasRole("USER")
                .antMatchers("/", "/home", "/css/**", "/img/**", "/account/join", "/board/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/account/login")
                .failureUrl("/account/login?error")
                .permitAll()
                .defaultSuccessUrl("/account/loginTest")
            .and()
                .logout()
                .logoutUrl("/account/logout")
                .permitAll()
                .logoutSuccessUrl("/account/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        final String usernameQuery = "select user_id, password, enabled "+
                                     "from user "+
                                     "where email = ?";
        final String authQuery = "select u.user_id, r.name as authority "+
                                 "from user_role ur inner join user u on ur.user_id = u.user_id "+
                                 "inner join role r on ur.role_id = r.role_id "+
                                 "where u.user_id = ?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usernameQuery)
                .authoritiesByUsernameQuery(authQuery);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}