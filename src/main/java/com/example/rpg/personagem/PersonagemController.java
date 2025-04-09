package com.example.rpg.personagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {
    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<String> salvarPersonagem(@RequestBody PersonagemDto dto){
        return new ResponseEntity<>(personagemService.salvar(dto.ToModel()), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonagemModel> buscarPorIdentificador(@PathVariable Long id){
        return ResponseEntity.ok(personagemService.buscarPersonagemPorIdentificador(id));
    }
}
