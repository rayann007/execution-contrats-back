package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.ContratPrestation;
import com.leoni.execution.contrats.Services.ContratPrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats-prestation")
public class ContratPrestationController {

    @Autowired
    private ContratPrestationService service;

    @GetMapping
    public List<ContratPrestation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ContratPrestation getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ContratPrestation create(@RequestBody ContratPrestation contratPrestation) {
        return service.create(contratPrestation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
