package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.Document;
import com.leoni.execution.contrats.Models.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    Document upload(Long contratId, MultipartFile file) throws IOException;
    byte[] download(Long documentId);
    List<Document> getDocumentsByContrat(Long contratId);
    boolean deleteById(Long id);
    boolean renameDocument(Long id, String newName);
    byte[] getDocumentsAsZip(Long contratId) throws IOException;

    List<Document> findByPersonnelId(Long personnelId);
    List<DocumentDTO> findSimpleByPersonnelId(Long personnelId);

}
