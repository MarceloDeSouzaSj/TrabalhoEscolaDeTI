package com.example.rpg.personagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface PersonagemRepository extends JpaRepository<PersonagemModel, Long> {
}
