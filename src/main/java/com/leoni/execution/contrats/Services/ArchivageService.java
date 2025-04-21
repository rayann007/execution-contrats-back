package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.ArchivageDocument;
import com.leoni.execution.contrats.Models.Document;
import java.util.List;

public interface ArchivageService {
    void archiverDocumentsContrat(List<Document> documents);
    List<ArchivageDocument> getAllArchivedDocuments();
    byte[] downloadArchivedDocument(Long documentId);

}


