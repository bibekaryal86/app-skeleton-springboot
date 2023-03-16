/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package spring.service.skeleton;

import static java.util.Collections.singletonMap;
import static spring.service.skeleton.app.util.CommonUtils.getSystemEnvProperty;
import static spring.service.skeleton.app.util.ConstantUtils.SERVER_PORT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    log.info("Begin application initialization...");
    SpringApplication app = new SpringApplication(App.class);
    app.setDefaultProperties(
        singletonMap("server.port", getSystemEnvProperty(SERVER_PORT, "8080")));
    app.run(args);
    log.info("End application initialization...");
  }
}
