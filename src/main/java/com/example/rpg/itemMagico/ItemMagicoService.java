package com.example.rpg.itemMagico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMagicoService {
    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public String salvarItemMagico(ItemMagicoModel item){
        return "teste";
    }

}
