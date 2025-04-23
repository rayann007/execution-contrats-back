package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Personnel;
import com.leoni.execution.contrats.Services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    @GetMapping
    public List<Personnel> getAllPersonnel() {
        return personnelService.getAll();
    }

    @GetMapping("/{id}")
    public Personnel getPersonnelById(@PathVariable Long id) {
        return personnelService.getById(id);
    }

    @PostMapping
    public Personnel createPersonnel(@RequestBody Personnel personnel) {
        return personnelService.create(personnel);
    }

    @PutMapping("/{id}")
    public Personnel updatePersonnel(@PathVariable Long id, @RequestBody Personnel personnel) {
        return personnelService.update(id, personnel);
    }

    @DeleteMapping("/{id}")
    public void deletePersonnel(@PathVariable Long id) {
        personnelService.delete(id);
    }

    @GetMapping("/service/{service}")
    public List<Personnel> getByService(@PathVariable String service) {
        return personnelService.getByService(service);
    }

    @GetMapping("/search")
    public List<Personnel> search(@RequestParam String keyword) {
        return personnelService.searchByNomOrFonction(keyword);
    }

    @GetMapping("/service/{service}/sorted")
    public List<Personnel> getByServiceSorted(@PathVariable String service) {
        return personnelService.getByServiceSortedByNom(service);
    }

}
