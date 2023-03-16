package health.data.java.app.util;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Endpoint(id = "clearCaches")
public class ActuatorUtilsClearCacheEndpoint {

  private final CacheManager cacheManager;

  public ActuatorUtilsClearCacheEndpoint(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @ReadOperation
  public ResponseEntity<String> clearCaches() {
    log.info("Firing Clear Caches Actuator!!!");
    cacheManager
        .getCacheNames()
        .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    return ResponseEntity.ok("{\"clearCaches\": \"successful\"}");
  }

  @ReadOperation
  public ResponseEntity<String> clearCache(@Selector String cacheNameSelector) {
    log.info("Firing Clear Cache Actuator: {} !!!", cacheNameSelector);
    Objects.requireNonNull(cacheManager.getCache(cacheNameSelector)).clear();
    return ResponseEntity.ok("{\"clearCache\": \"successful\"}");
  }
}
