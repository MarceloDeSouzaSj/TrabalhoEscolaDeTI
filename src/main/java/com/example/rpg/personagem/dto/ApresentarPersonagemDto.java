package com.example.rpg.personagem.dto;

import com.example.rpg.enums.ClassePersonagem;
import com.example.rpg.itemMagico.ItemMagicoDto;
import com.example.rpg.itemMagico.ItemMagicoModel;

import java.util.List;

public class ApresentarPersonagemDto {
    private String nome;
    private String nomeAventureiro;
    private ClassePersonagem classe;
    private int level;
    private int forca;
    private int defesa;

    public ApresentarPersonagemDto(String nome, String nomeAventureiro, ClassePersonagem classe, int level, int forca, int defesa, List<ItemMagicoModel> itens) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;

        for (ItemMagicoModel item : itens) {
            forca += item.getForca();
            defesa += item.getDefesa();
        }
        this.forca = forca;
        this.defesa = defesa;
    }

    public ApresentarPersonagemDto() {
    }

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
}
