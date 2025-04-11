package com.example.rpg.personagem;

import com.example.rpg.itemMagico.ItemMagicoModel;
import com.example.rpg.personagem.dto.ApresentarPersonagemDto;
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
    @Operation(summary = "Adicionar item mágico ao personagem", description = "Insira o identificador do item mágico e do personagem <br>" +
            "Cada item mágico deve estar vinculado a somente um personagem")
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
        try {
            ApresentarPersonagemDto personagem = personagemService.buscarPersonagemPorIdentificador(identificador);
            return ResponseEntity.status(HttpStatus.OK).body(personagem);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("ListarItensMagicos/{identificador}")
    @Operation(summary = "Listar itens mágicos do personagem", description = "Insira o identificador do personagem")
    public ResponseEntity<?> listarItensMagicosDoPersonagem(@PathVariable Long identificador) {
        try {
            List<ItemMagicoModel> itens = personagemService.listarItemMagicoDoPersonagem(identificador);
            return ResponseEntity.status(HttpStatus.OK).body(itens);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("BuscarAmuleto/{identificador}")
    @Operation(summary = "Buscar amuleto do personagem", description = "Insira o identificador do personagem")
    public ResponseEntity<?> buscarAmuleto(@PathVariable Long identificador) {
        try {
            ItemMagicoModel item = personagemService.buscarAmuleto(identificador);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping()
    @Operation(summary = "Listar personagens")
    public ResponseEntity<?> ListarPersonagem() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personagemService.listarPersonagem());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("{identificador}")
    @Operation(summary = "Deletar personagem")
    public ResponseEntity<?> deletarPorId(@PathVariable Long identificador) {
        return ResponseEntity.status(HttpStatus.OK).body(personagemService.deletarPersonagem(identificador));
    }

    @DeleteMapping("{identificadorDoItem}/{identificadorDoPersonagem}")
    @Operation(summary = "Remove o item mágico do personagem", description = "Insira o identificador do item mágico e do personagem <br>")
    public ResponseEntity<?> removerItemMagicoDoPersonagem(@PathVariable Long identificadorDoItem, @PathVariable Long identificadorDoPersonagem) {
        try {
            return ResponseEntity.ok(personagemService.RemoverItemMagicoDoPersonagem(identificadorDoItem, identificadorDoPersonagem));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
