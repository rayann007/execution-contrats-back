package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.EvaluationQualite;
import com.leoni.execution.contrats.Models.EvaluationQualiteDTO;
import com.leoni.execution.contrats.Models.ServiceType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EvaluationQualiteService {
    void enregistrer(ServiceType service, String remarque, MultipartFile fichier, Long chefId) throws IOException;
    List<EvaluationQualite> getByService(ServiceType service);
    byte[] download(Long id);
    List<EvaluationQualiteDTO> getEvaluationsByUtilisateur(Long utilisateurId);

}
