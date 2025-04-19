package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.ArchivageDocument;
import com.leoni.execution.contrats.Services.ArchivageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
