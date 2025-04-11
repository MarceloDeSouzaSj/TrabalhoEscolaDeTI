package com.example.rpg.personagem.dto;

import com.example.rpg.enums.ClassePersonagem;
import com.example.rpg.personagem.PersonagemModel;

public class PersonagemDto {
    private String nome;
    private String nomeAventureiro;
    private ClassePersonagem classe;
    private int level;
    private int forca;
    private int defesa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public ClassePersonagem getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public PersonagemDto() {
    }

    public PersonagemDto(String nome, String nomeAventureiro, ClassePersonagem classe, int level, int forca, int defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public PersonagemModel ToModel (){
        return new PersonagemModel(nome, nomeAventureiro, classe, level, forca, defesa);
    }
}
