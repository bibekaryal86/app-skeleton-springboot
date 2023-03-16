package spring.service.skeleton.app.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppTestController {
  @CrossOrigin
  @GetMapping(value = "/tests/ping", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> pingTest() {
    return ResponseEntity.ok("{\"ping\": \"successful\"}");
  }
}
