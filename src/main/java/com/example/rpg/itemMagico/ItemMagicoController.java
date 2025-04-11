package com.example.rpg.itemMagico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemMagico")
@Tag(name = "Itens mágicos", description = "Gerencia os itens mágicos do jogo")
public class ItemMagicoController {

    @Autowired
    ItemMagicoService itemMagicoService;

    @PostMapping
    @Operation(summary = "Cria um novo item", description = "Para criação você pode colocar na classe os valores: 'Arma', 'Armadura', 'Amuleto' <br>" +
            "Quando um Item for do Tipo Arma, a Defesa dele será OBRIGATORIAMENTE zero. <br>" +
            "Quando um Item for do Tipo Armadura, a Força dele será OBRIGATORIAMENTE zero. <br>" +
            "Quando um Item for do Tipo Amuleto, ele poderá ter Força e Defesa, porém o itemMagico só pode possuir 1 Item Mágico do tipo Amuleto. <br>" +
            "Os atributos Força e Defesa, podem ser no máximo 10. <br>" +
            "Não podem existir Itens com zero de Defesa e zero de Força.")
    public ResponseEntity<?> salvar(@RequestBody ItemMagicoDto dto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(itemMagicoService.salvarItemMagico(dto.toModel()));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("{identificador}")
    @Operation(summary = "Buscar item mágico por identificador", description = "Insira o identificador do item mágico")
    public ResponseEntity<?> buscarPorIdentificador(@PathVariable Long identificador) {
        ItemMagicoModel itemMagico = itemMagicoService.buscarItemPorIdentificador(identificador);
        if (itemMagico == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item mágico " + identificador + " não encontrado!");

        return ResponseEntity.status(HttpStatus.OK).body(itemMagico);

    }

    @GetMapping()
    @Operation(summary = "Listar itens")
    public ResponseEntity<List<ItemMagicoModel>> ListarPersonagem() {
        return ResponseEntity.status(HttpStatus.OK).body(itemMagicoService.listarItens());
    }
}
