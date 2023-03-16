package spring.service.skeleton.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.service.skeleton.app.util.InterceptorUtilsLogging;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new InterceptorUtilsLogging());
  }
}
