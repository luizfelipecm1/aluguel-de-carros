package com.example.alugueldeveiculos.model.enums;

public enum UsuarioEnum {
    CLIENTE,
    AGENTE;

    @Override
    public String toString() {
        return this.name();
    }
}
