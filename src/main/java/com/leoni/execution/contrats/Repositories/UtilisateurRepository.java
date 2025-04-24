package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByRole(Utilisateur.RoleName role);
    List<Utilisateur> findByServiceAffectation(String service);
    Optional<Utilisateur> findByEmail(String email);

}
