package com.example.rpg.personagem;

import com.example.rpg.personagem.dto.PersonagemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagem")
@Tag(name = "Personagens", description = "Gerencia os personagens do jogo")
public class PersonagemController {
    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    @Operation(summary = "Cria um novo personagem", description = "Para criação você pode colocar na classe os valores: 'Guerreiro', 'Mago', 'Arqueiro', 'Ladino' ou 'Bardo' <br>" +
            "e também os valores de força e defesa somados não podem ultrapassar 10")
    public ResponseEntity<?> salvarPersonagem(@RequestBody PersonagemDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(personagemService.salvar(dto.ToModel()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }

    @PutMapping("{identificador}")
    @Operation(summary = "Atualizar nome do personagem", description = "Insira o identificador do personagem e coloque somente o nome para ser alterado no request body")
    public ResponseEntity<?> atualizarNomePorIdentificador(@PathVariable Long identificador, @RequestBody String nome) {
        try {
            PersonagemModel personagemAtualizado = personagemService.atualizarNomePorId(identificador, nome);
            return ResponseEntity.ok(personagemAtualizado);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("{identificadorDoItem}/{identificadorDoPersonagem}")
    @Operation(summary = "Adicionar item mágico ao personagem", description = "Insira o identificador do item mágico e do personagem")
    public ResponseEntity<?> adicionarItemMagicoAoPersonagem(@PathVariable Long identificadorDoItem, @PathVariable Long identificadorDoPersonagem) {
        try {
            return ResponseEntity.ok(personagemService.colocarItemMagicoNoPersonagem(identificadorDoItem, identificadorDoPersonagem));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("{identificador}")
    @Operation(summary = "Buscar personagem por identificador", description = "Insira o identificador do personagem")
    public ResponseEntity<?> buscarPorIdentificador(@PathVariable Long identificador) {
        PersonagemModel personagem = personagemService.buscarPersonagemPorIdentificador(identificador);
        if (personagem == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem " + identificador + " não encontrado!");

        return ResponseEntity.status(HttpStatus.OK).body(personagem);

    }

    @GetMapping()
    @Operation(summary = "Listar personagens")
    public ResponseEntity<List<PersonagemModel>> ListarPersonagem() {
        return ResponseEntity.status(HttpStatus.OK).body(personagemService.listarPersonagem());
    }

    @DeleteMapping("{identificador}")
    @Operation(summary = "Deletar personagem")
    public ResponseEntity<?> deletarPorId(@PathVariable Long identificador) {
        return ResponseEntity.status(HttpStatus.OK).body(personagemService.deletarPersonagem(identificador));
    }
}
