package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.ContratTravaux;
import com.leoni.execution.contrats.Services.ContratTravauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats-travaux")
public class ContratTravauxController {

    @Autowired
    private ContratTravauxService service;

    @GetMapping
    public List<ContratTravaux> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ContratTravaux getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ContratTravaux create(@RequestBody ContratTravaux contratTravaux) {
        return service.create(contratTravaux);
    }

    @PutMapping("/{id}")
    public ContratTravaux update(@PathVariable Long id, @RequestBody ContratTravaux contratTravaux) {
        return service.update(id, contratTravaux);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
