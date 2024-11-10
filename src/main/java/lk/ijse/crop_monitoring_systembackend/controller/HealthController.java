package lk.ijse.crop_monitoring_systembackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    @GetMapping
    public String checkHealth(){
        System.out.println("Crop Monitoring System API is Running");
        return "Crop Monitoring System API is Running";
    }
}
