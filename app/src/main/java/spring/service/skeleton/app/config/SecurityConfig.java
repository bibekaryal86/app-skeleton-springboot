package spring.service.skeleton.app.config;

import static org.springframework.security.config.Customizer.withDefaults;
import static spring.service.skeleton.app.util.CommonUtils.getSystemEnvProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import spring.service.skeleton.app.util.ConstantUtils;

@Configuration
@EnableWebSecurity
@Profile("!springboottest")
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(
            auth -> auth.requestMatchers("/tests/ping").permitAll().anyRequest().authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(withDefaults())
        .build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    final UserDetails user =
        User.withUsername(getSystemEnvProperty(ConstantUtils.BASIC_AUTH_USR, null))
            .password("{noop}".concat(getSystemEnvProperty(ConstantUtils.BASIC_AUTH_PWD, null)))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
  }
}
