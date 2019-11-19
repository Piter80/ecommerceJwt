package my.petr.ecommerce.config;

import my.petr.ecommerce.security.AuthenticationFailureHandler;
import my.petr.ecommerce.security.AuthenticationSuccessHandler;
import my.petr.ecommerce.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
    return new TokenAuthenticationFilter();
  }

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)

        .authorizeRequests()

        .anyRequest().authenticated()

        .antMatchers("/user/**").permitAll()
        .and().formLogin().successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler)

        .and().csrf().disable();
  }
}
