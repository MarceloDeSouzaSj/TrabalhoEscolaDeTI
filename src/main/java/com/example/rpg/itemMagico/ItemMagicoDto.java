package com.example.rpg.itemMagico;

import com.example.rpg.enums.TipoDoItem;

public class ItemMagicoDto {

    private String nome;
    private TipoDoItem tipoDoItem;
    private int forca;
    private int defesa;

    public String getNome() {
        return nome;
    }

    public ItemMagicoDto(String nome, TipoDoItem tipoDoItem, int forca, int defesa) {
        this.nome = nome;
        this.tipoDoItem = tipoDoItem;
        this.forca = forca;
        this.defesa = defesa;
    }

    public int somarAtributos(){
        return forca + defesa;
    }

    public ItemMagicoModel toModel(){
        return new ItemMagicoModel(nome, tipoDoItem, forca, defesa);
    }

    public ItemMagicoDto() {
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



}
