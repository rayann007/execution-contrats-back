package com.leoni.execution.contrats.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/contrats")
    public ResponseEntity<?> getAllContrats() {
        return ResponseEntity.ok("Liste complète des contrats (admin only)");
    }
}
