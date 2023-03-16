package spring.service.skeleton.app.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static spring.service.skeleton.app.util.CommonUtils.getSystemEnvProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import spring.service.skeleton.app.util.ConstantUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(STATELESS);
    return httpSecurity.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web ->
        web
            // .ignoring()
            // .requestMatchers("/swagger-ui/")
            // .and()
            .ignoring()
            .requestMatchers(GET, "/tests/ping");
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user =
        User.withUsername(getSystemEnvProperty(ConstantUtils.BASIC_AUTH_USR, null))
            .password("{noop}".concat(getSystemEnvProperty(ConstantUtils.BASIC_AUTH_PWD, null)))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
  }
}
