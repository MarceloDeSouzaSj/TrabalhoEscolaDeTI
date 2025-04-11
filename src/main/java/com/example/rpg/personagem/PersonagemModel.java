package com.example.rpg.personagem;

import com.example.rpg.enums.ClassePersonagem;
import com.example.rpg.itemMagico.ItemMagicoModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_personagem")
public class PersonagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String nomeAventureiro;
    @Column(nullable = false)
    private ClassePersonagem classe;

    @Column(nullable = false)
    private int level;
    @Column(nullable = false)
    private int forca;
    @Column(nullable = false)
    private int defesa;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemMagicoModel> itensMagicos = new ArrayList<>();

    public List<ItemMagicoModel> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagicoModel> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

    public void adicionarItemMagico(ItemMagicoModel item){
        itensMagicos.add(item);
    }

    public ClassePersonagem getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public PersonagemModel() {
    }

    public PersonagemModel(String nome, String nomeAventureiro, ClassePersonagem classe, int level, int forca, int defesa, List<ItemMagicoModel> itensMagicos) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
        this.itensMagicos = itensMagicos;
    }

    public PersonagemModel(String nome, String nomeAventureiro, ClassePersonagem classe, int level, int forca, int defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getIdentificador() {
        return identificador;
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
