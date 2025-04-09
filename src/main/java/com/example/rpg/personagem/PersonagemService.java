package com.example.rpg.personagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonagemService {
    @Autowired
    private PersonagemRepository personagemRepository;

    public PersonagemModel salvar(PersonagemModel personagem){
        if(personagem.getDefesa() + personagem.getForca() > 10)
            return null;

        return personagemRepository.save(personagem);
    }

    public PersonagemModel buscarPersonagemPorIdentificador(Long id){
        return personagemRepository.findById(id).orElse(null);
    }
}
