package spring.service.skeleton.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import spring.service.skeleton.app.util.ConstantUtils;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static spring.service.skeleton.app.util.CommonUtils.getSystemEnvProperty;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                //.ignoring()
                //.antMatchers("/swagger-ui/")
                //.and()
                .ignoring()
                .mvcMatchers(GET, "/tests/ping");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(getSystemEnvProperty(ConstantUtils.BASIC_AUTH_USR, null))
                .password("{noop}".concat(getSystemEnvProperty(ConstantUtils.BASIC_AUTH_PWD, null)))
                .roles("USER");
    }
}
