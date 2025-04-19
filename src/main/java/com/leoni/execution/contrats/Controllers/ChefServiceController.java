package com.leoni.execution.contrats.Controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ChefServiceController {

    @GetMapping("/contrats-continus")
    public ResponseEntity<?> getContratsContinus(Authentication auth) {
        String email = auth.name();
        // filtre des contrats continus selon serviceAffectation
        return ResponseEntity.ok("Contrats continus accessibles par le chef service : " + email);
    }
}
