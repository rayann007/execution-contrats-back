package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.DelaiContractuel;
import com.leoni.execution.contrats.Services.DelaiContractuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delais")
public class DelaiContractuelController {

    @Autowired
    private DelaiContractuelService service;

    @PostMapping
    public DelaiContractuel create(@RequestBody DelaiContractuel delai) {
        return service.create(delai);
    }

    @GetMapping("/contrat/{id}")
    public List<DelaiContractuel> getByContrat(@PathVariable Long id) {
        return service.getByContrat(id);
    }


}
