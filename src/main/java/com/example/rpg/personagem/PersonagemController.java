package com.example.rpg.personagem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personagem")
@Tag(name = "Personagens", description = "Gerencia os personagens do jogo")
public class PersonagemController {
    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    @Operation(summary = "Cria um novo personagem", description = "Para criação você pode colocar na classe os valores: 'Guerreiro', 'Mago', 'Arqueiro', 'Ladino' ou 'Bardo' <br>" +
            "e também os valores de força e defesa somados não podem ultrapassar 10")
    public ResponseEntity<?> salvarPersonagem(@RequestBody PersonagemDto dto){
        PersonagemModel personagemCriado = personagemService.salvar(dto.ToModel());
        if(personagemCriado == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("O personagem não pode ter mais de 10 pontos de atributo!");

        return ResponseEntity.status(HttpStatus.CREATED).body(personagemCriado);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorIdentificador(@PathVariable Long id){
        PersonagemModel personagem = personagemService.buscarPersonagemPorIdentificador(id);
        if(personagem == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem " + id +  " não encontrado!");

        return ResponseEntity.status(HttpStatus.OK).body(personagem);

    }
}
