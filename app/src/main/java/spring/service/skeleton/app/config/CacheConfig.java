package spring.service.skeleton.app.config;

import static java.util.Objects.requireNonNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableCaching
@EnableScheduling // so that @Scheduled will be registered
public class CacheConfig {

  private final CacheManager cacheManager;

  public CacheConfig(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Scheduled(cron = "0 0 0 * * *")
  protected void putAllCache() {
    log.info("Firing Cache Evict!!!");
    cacheManager
        .getCacheNames()
        .forEach(cacheName -> requireNonNull(cacheManager.getCache(cacheName)).clear());

    log.info("Firing All Cache!!!");
    // call methods that set to cache here
  }
}
