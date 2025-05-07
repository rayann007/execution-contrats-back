package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Utilisateur;
import com.leoni.execution.contrats.Security.AuthRequest;
import com.leoni.execution.contrats.Security.AuthResponse;
import com.leoni.execution.contrats.Security.JwtUtils;
import com.leoni.execution.contrats.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Endpoint de connexion sécurisé
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Utilisateur utilisateur = utilisateurService.findByEmail(request.getEmail());

        if (utilisateur == null || !passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new RuntimeException("Identifiants invalides !");
        }

        String token = jwtUtils.generateJwt(utilisateur.getEmail(),utilisateur.getRole().name());
        return new AuthResponse(token, utilisateur.getRole().name());
    }


}
