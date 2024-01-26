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
@RequestMapping("/jeux")
public class JeuControllers {

    private final JeuRepository jeuRepository;
    private final AvisRepository avisRepository;

    public JeuControllers(JeuRepository jeuRepository, AvisRepository avisRepository) {
        this.jeuRepository = jeuRepository;
        this.avisRepository = avisRepository;
    }

    @GetMapping(value = "/")
    public List<Jeu> index(){
        return jeuRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Jeu getOne(@PathVariable(name = "id", required = false) Jeu jeu){

        if (jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technically, there is no !!!");
        }else {
            return jeu;
        }

    }

    @PostMapping(value = "/")
    public ResponseEntity<Jeu> createJeu(@Valid @RequestBody Jeu jeu, BindingResult bindingResult){

        jeuRepository.save(jeu);
        return new ResponseEntity<>(jeu, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id", required = false) Jeu jeu){
        if (jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technically, there is no !!!");
        }else {
            jeuRepository.delete(jeu);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Jeu> update (@PathVariable(name = "id", required = false) Jeu jeu,
                                            @Valid @RequestBody Jeu jeuUpdate, BindingResult bindingResult){

        if(jeu == null){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technically, there is no !!!");
        }else {
            if (bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            }else {
                jeuUpdate.setId(jeu.getId());
                jeuRepository.save(jeuUpdate);
                return new ResponseEntity<>(jeuUpdate, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/{id}/dernierAvis")
    public Avis getOneLastAvis(@PathVariable(name = "id", required = false) Jeu jeu){

        if (jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technically, there is no !!!");
        }else {

            Avis avis = avisRepository.findLastAvis(jeu);
            return avis;
        }

    }
}
