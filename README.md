# spring-service-skeleton

* This is a template Repository to create a new Spring Boot REST Service
* Things to update:
    * Refactor the package name from `spring.service.skeleton.app` to as desired
        * keep it 3 words if possible, eg: `xxx.xxx.xxx.app`
    * `settings.gradle`
        * `rootProject.name`
    * `build.gradle`
        * Add/Remove dependencies as necessary
        * `springVersion`, and version in buildscript and plugin as necessary
        * `archiveFileName` in `bootJar`
        * `mainClass` in `application`
    * gradle wrapper version as necessary
    * `application.yml` as necessary
        * at least need to replace `spring-service-skeleton` with application name
    * `logback.xml` as necessary
        * avoid LOG_FILE as much as possible, prefer console logging
        * replace `spring-service-skeleton` with application name in `LOG_PATTERN` and `LOG_FILE`
        * remove `traceId` and `spanId` if `spring-cloud-started-sleuth` is not used in `build.gradle`
    * `Dockerfile` as necessary
        * esp `JAR_FILE`, `COPY` and environment variables in `ENTRYPOINT`
    * GCP configurations, in `gcp` folder as necessary
        * esp `app-credentials.yaml` and `app-credentials_DUMMY.yaml`
    * `README.md` i.e. this file to add the program's readme
    * `.gitignore` if necessary
 * Things to add:
     * `DatasourceConfig` if using MONGO/JPA/JDBC
         * See: `pets-database-layer` for MongoDB example
             * https://github.com/bibekaryal86/pets-database-layer
         * See: `health-data-java` for JPA example
             * https://github.com/bibekaryal86/health-data-java
         * For JDBC, only need to set `Datasource` from above examples
     * `RestTemplateConfig` if using `RestTemplate`
         * See: `pets-service-layer` for example
             * https://github.com/bibekaryal86/pets-service-layer
     * `SwaggerConfig` if using SwaggerUI
         * See: `pets-service-layer` / `pets-database-layer` for example
             * https://github.com/bibekaryal86/pets-service-layer
             * https://github.com/bibekaryal86/pets-database-layer
         * Also, will have to update `SecurityConfig` to allow SwaggerUI
 * Things to remove:
     * If not using cache
         * Remove `CacheConfig` from config package
         * Remove `spring-boot-starter-cache` dependency from `build.gradle`
     * GitHub workflows
         * Remove `dependabot.yml` in the new app until automated merge is figured out
