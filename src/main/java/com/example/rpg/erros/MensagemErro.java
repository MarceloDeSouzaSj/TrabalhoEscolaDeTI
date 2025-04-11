package com.example.rpg.erros;

public class MensagemErro extends RuntimeException {
    public MensagemErro(String message) {
        super(message);
    }
}
