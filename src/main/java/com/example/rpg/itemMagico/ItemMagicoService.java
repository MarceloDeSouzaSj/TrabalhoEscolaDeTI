package com.example.rpg.itemMagico;

import com.example.rpg.erros.MensagemErro;
import com.example.rpg.personagem.PersonagemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemMagicoService {
    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public ItemMagicoModel salvarItemMagico(ItemMagicoModel item) {

        switch (item.getTipoDoItem()) {
            case Arma -> {
                if (item.getDefesa() != 0) item.setDefesa(0);
            }
            case Armadura -> {
                if (item.getForca() != 0) item.setForca(0);
            }
            case Amuleto -> {
                if (item.getForca() + item.getDefesa() > 10)
                    throw new MensagemErro("Os itens magicos não podem ter os atributos somados maior que 10");
            }
            default -> {
            }
        }

        if (item.getForca() == 0 && item.getDefesa() == 0)
            throw new MensagemErro("Os itens mágicos não podem ter os atributos igual a 0");



        return itemMagicoRepository.save(item);

    }

    public ItemMagicoModel buscarItemPorIdentificador(Long id) {
        return itemMagicoRepository.findById(id).orElse(null);
    }

    public List<ItemMagicoModel> listarItens() {
        return itemMagicoRepository.findAll();
    }

}
