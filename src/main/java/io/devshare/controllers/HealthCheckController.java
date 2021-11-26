package io.devshare.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 서버가 살아있는지 확인 담당.
 */
@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

    @GetMapping("")
    public String healthCheck() {
        return "I am Alive.";
    }
}
