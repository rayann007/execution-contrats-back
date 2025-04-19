package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.Document;
import com.leoni.execution.contrats.Repositories.ContratRepository;
import com.leoni.execution.contrats.Repositories.DocumentRepository;
import com.leoni.execution.contrats.Services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ContratRepository contratRepository;

    @Override
    public Document upload(Long contratId, MultipartFile file) throws IOException {
        Contrat contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable"));

        Document doc = new Document();
        doc.setNomFichier(file.getOriginalFilename());
        doc.setFichier(file.getBytes());
        doc.setDateCreation(LocalDate.now());
        doc.setContrat(contrat);

        return documentRepository.save(doc);
    }

    @Override
    public byte[] download(Long documentId) {
        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document introuvable"));
        return doc.getFichier();
    }

    @Override
    public List<Document> getDocumentsByContrat(Long contratId) {
        return documentRepository.findByContrat_Id(contratId);
    }

    @Override
    public boolean deleteById(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean renameDocument(Long id, String newName) {
        Optional<Document> docOpt = documentRepository.findById(id);
        if (docOpt.isPresent()) {
            Document doc = docOpt.get();
            doc.setNomFichier(newName);
            documentRepository.save(doc);
            return true;
        }
        return false;
    }


        @Override
        public byte[] getDocumentsAsZip(Long contratId) throws IOException {
            // Utilise findByContrat_Id comme expliqué précédemment
            List<Document> docs = documentRepository.findByContrat_Id(contratId);
            if (docs.isEmpty()) return new byte[0];

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);

            for (Document doc : docs) {
                ZipEntry entry = new ZipEntry(doc.getNomFichier());
                zos.putNextEntry(entry);
                zos.write(doc.getFichier()); // fichier est bien un byte[]
                zos.closeEntry();
            }

            zos.close();
            return baos.toByteArray();
        }
    }


