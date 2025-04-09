package com.example.rpg.personagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonagemService {
    @Autowired
    private PersonagemRepository personagemRepository;

    public String salvar(PersonagemModel personagem){
        if(personagem.getDefesa() + personagem.getForca() > 10)
            return "O personagem não pode ter mais de 10 pontos de atributo!";

        personagemRepository.save(personagem);
        return "Personagem criado com sucesso! Identificador: " + personagem.getIdentificador();
    }

    public PersonagemModel buscarPersonagemPorIdentificador(Long id){
        return personagemRepository.findById(id).get();
    }
}
