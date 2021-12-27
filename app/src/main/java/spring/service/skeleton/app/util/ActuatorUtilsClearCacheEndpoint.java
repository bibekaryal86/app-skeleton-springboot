package spring.service.skeleton.app.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Slf4j
@Component
@Endpoint(id = "clearCaches")
public class ActuatorUtilsClearCacheEndpoint {

    private final CacheManager cacheManager;

    public ActuatorUtilsClearCacheEndpoint(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @ReadOperation
    public String clearCaches() {
        log.info("Firing Clear Caches Actuator!!!");
        cacheManager.getCacheNames()
                .forEach(cacheName -> requireNonNull(cacheManager.getCache(cacheName)).clear());
        return "Finished Clear Caches Actuator!!!";
    }

    @ReadOperation
    public String clearCache(@Selector String cacheNameSelector) {
        log.info("Firing Clear Cache Actuator: {} !!!", cacheNameSelector);
        requireNonNull(cacheManager.getCache(cacheNameSelector)).clear();
        return String.format("Finished Clear Cache Actuator: %s !!!", cacheNameSelector);
    }
}
