package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.ContratContinue;
import com.leoni.execution.contrats.Services.ContratContinueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats-continue")
public class ContratContinueController {

    @Autowired
    private ContratContinueService contratContinueService;

    @GetMapping
    public List<ContratContinue> getAll() {
        return contratContinueService.getAll();
    }

    @GetMapping("/{id}")
    public ContratContinue getById(@PathVariable Long id) {
        return contratContinueService.getById(id);
    }

    @PostMapping
    public ContratContinue create(@RequestBody ContratContinue contratContinue) {
        return contratContinueService.create(contratContinue);
    }

    @PutMapping("/{id}")
    public ContratContinue update(@PathVariable Long id, @RequestBody ContratContinue contratContinue) {
        return contratContinueService.update(id, contratContinue);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contratContinueService.delete(id);
    }
}
