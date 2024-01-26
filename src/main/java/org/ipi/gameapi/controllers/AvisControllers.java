package org.ipi.gameapi.controllers;

import jakarta.validation.Valid;
import org.ipi.gameapi.models.Avis;
import org.ipi.gameapi.models.Jeu;
import org.ipi.gameapi.repositories.AvisRepository;
import org.ipi.gameapi.repositories.JeuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/avis")
public class AvisControllers {

    private final AvisRepository avisRepository;
    private final JeuRepository jeuRepository;

    public AvisControllers(AvisRepository avisRepository, JeuRepository jeuRepository) {
        this.avisRepository = avisRepository;
        this.jeuRepository = jeuRepository;
    }

    @GetMapping(value = "/")
    public List<Avis> index(){
        return avisRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Avis getOne(@PathVariable(name = "id", required = false) Avis avis){

        if (avis == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technically, there is no !!!");
        }else {
            return avis;
        }

    }

    @GetMapping(value = "/jeux/{id}")
    public List<Avis> getOne(@PathVariable(name = "id", required = false) Jeu jeu){

        if (jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technically, there is no !!!");
        }else {

            return avisRepository.findAllAvisByJeu(jeu);
        }

    }

    @PostMapping(value = "/")
    public ResponseEntity<Avis> createJeu(@Valid @RequestBody Avis avis, BindingResult bindingResult){

            avisRepository.save(avis);
            return new ResponseEntity<>(avis, HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id", required = false) Avis avis){
        if (avis == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technically, there is no !!!");
        }else {
            avisRepository.delete(avis);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Avis> update (@PathVariable(name = "id", required = false) Avis avis,
                                       @Valid @RequestBody Avis avisUpdate, BindingResult bindingResult){

        if(avis == null){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            if (bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            }else {
                avisUpdate.setId(avis.getId());
                avisRepository.save(avisUpdate);
                return new ResponseEntity<>(avisUpdate, HttpStatus.OK);
            }
        }
    }

}
