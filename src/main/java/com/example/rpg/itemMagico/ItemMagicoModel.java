package com.example.rpg.itemMagico;

import com.example.rpg.enums.TipoDoItem;
import com.example.rpg.personagem.PersonagemModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@Entity
@Table(name = "tbl_item_magico")
public class ItemMagicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;
    private String nome;
    private TipoDoItem tipoDoItem;
    private int forca;
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    @JsonBackReference
    private PersonagemModel personagem;

    public ItemMagicoModel() {
    }

    public PersonagemModel getPersonagem() {
        return personagem;
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

    public TipoDoItem getTipoDoItem() {
        return tipoDoItem;
    }

    public void setTipoDoItem(TipoDoItem tipoDoItem) {
        this.tipoDoItem = tipoDoItem;
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

    public void setPersonagem(PersonagemModel personagem) {
        this.personagem = personagem;
    }

    public ItemMagicoModel(String nome, TipoDoItem tipoDoItem, int forca, int defesa) {
        this.nome = nome;
        this.tipoDoItem = tipoDoItem;
        this.forca = forca;
        this.defesa = defesa;
    }

}
