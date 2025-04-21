package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.ArchivageDocument;
import com.leoni.execution.contrats.Models.Document;
import com.leoni.execution.contrats.Repositories.ArchivageDocumentRepository;
import com.leoni.execution.contrats.Services.ArchivageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArchivageServiceImpl implements ArchivageService {

    @Autowired
    private ArchivageDocumentRepository archivageRepo;

    @Override
    public void archiverDocumentsContrat(List<Document> documents) {
        for (Document doc : documents) {
            if (!archivageRepo.existsByDocument(doc)) {
                ArchivageDocument archive = new ArchivageDocument();
                archive.setDateArchivage(LocalDate.now());
                archive.setDocument(doc);
                archivageRepo.save(archive);
            }
        }
    }

    @Override
    public List<ArchivageDocument> getAllArchivedDocuments() {
        return archivageRepo.findAll();
    }

    @Override
    public byte[] downloadArchivedDocument(Long documentId) {
        return archivageRepo.findAll().stream()
                .filter(a -> a.getDocument().getId().equals(documentId))
                .findFirst()
                .map(a -> a.getDocument().getFichier())
                .orElseThrow(() -> new RuntimeException("Document archivé introuvable"));
    }

    @Override
    public Document getDocumentById(Long documentId) {
        ArchivageDocument archive = archivageRepo.findByDocument_Id(documentId)
                .orElseThrow(() -> new RuntimeException("Document archivé non trouvé avec l'id " + documentId));
        return archive.getDocument();
    }



}
