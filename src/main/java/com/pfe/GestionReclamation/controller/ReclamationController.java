

package com.pfe.GestionReclamation.controller;


import com.pfe.GestionReclamation.model.Reclamation;
import com.pfe.GestionReclamation.service.Reclamation.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/reclamation/")
public class ReclamationController {

    @Autowired
    ReclamationService reclamationService;

    @GetMapping("list")
    public List<Reclamation> findAll() {
        return reclamationService.findAll();
    }

    @PostMapping("add")
    public Reclamation add(@RequestBody Reclamation reclamation) {
        return reclamationService.add(reclamation);
    }

    @PutMapping("update/{id}")
    public Reclamation update(@RequestBody Reclamation reclamation, @PathVariable("id") Long id) {
        return reclamationService.update(reclamation, id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") long id) {
        reclamationService.delete(id);
    }


    @GetMapping("findById/{id}")
    public Reclamation findById(@PathVariable("id") Long id) {
        return reclamationService.findById(id);
    }


}
