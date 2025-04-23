package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByContrat_Id(Long contratId);

    List<Document> findByContratIdIn(List<Long> contratIds);


}
