package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.ArchivageDocument;
import com.leoni.execution.contrats.Services.ArchivageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leoni.execution.contrats.Models.Document;

import java.util.List;

@RestController
@RequestMapping("/api/archives")
public class ArchivageController {

    @Autowired
    private ArchivageService archivageService;

    @GetMapping
    public ResponseEntity<List<ArchivageDocument>> getArchivedDocuments() {
        List<ArchivageDocument> archives = archivageService.getAllArchivedDocuments();
        return ResponseEntity.ok(archives);
    }
    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> downloadArchivedDocument(@PathVariable Long documentId) {
        // Récupération du fichier depuis le service
        byte[] fichier = archivageService.downloadArchivedDocument(documentId);

        // Récupération du document pour avoir son nom
        Document document = archivageService.getDocumentById(documentId);

        // Préparation des headers de téléchargement
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(document.getNomFichier()) // 👈 nom réel du fichier
                .build());

        return new ResponseEntity<>(fichier, headers, HttpStatus.OK);
    }



}
