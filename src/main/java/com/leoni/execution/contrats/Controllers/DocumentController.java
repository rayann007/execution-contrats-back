package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Document;
import com.leoni.execution.contrats.Models.DocumentDTO;
import com.leoni.execution.contrats.Services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("contratId") Long contratId) {
        try {
            documentService.upload(contratId, file);
            return ResponseEntity.ok("Fichier uploadé avec succès !");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erreur lors de l'upload du fichier.");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        byte[] fileContent = documentService.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document_" + id)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileContent);
    }

    @GetMapping("/by-contrat/{contratId}")
    public ResponseEntity<List<Document>> getDocumentsByContrat(@PathVariable Long contratId) {
        List<Document> docs = documentService.getDocumentsByContrat(contratId);
        return ResponseEntity.ok(docs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        boolean deleted = documentService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Document supprimé !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document introuvable.");
        }
    }
    @PutMapping("/{id}/rename")
    public ResponseEntity<String> renameDocument(@PathVariable Long id,
                                                 @RequestParam String newName) {
        boolean renamed = documentService.renameDocument(id, newName);
        if (renamed) {
            return ResponseEntity.ok("Nom du document modifié !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document introuvable.");
        }
    }
    @GetMapping("/zip-by-contrat/{contratId}")
    public ResponseEntity<byte[]> downloadDocumentsAsZip(@PathVariable Long contratId) throws IOException {
        byte[] zipData = documentService.getDocumentsAsZip(contratId);

        if (zipData.length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("documents_contrat_" + contratId + ".zip")
                .build());

        return new ResponseEntity<>(zipData, headers, HttpStatus.OK);
    }

    @GetMapping("/documents/personnel/{personnelId}")
    public List<Document> getDocumentsByPersonnel(@PathVariable Long personnelId) {
        return documentService.findByPersonnelId(personnelId);
    }


    @GetMapping("/simple/personnel/{personnelId}")
    public List<DocumentDTO> getSimpleDocsByPersonnel(@PathVariable Long personnelId) {
        return documentService.findSimpleByPersonnelId(personnelId);
    }


}
