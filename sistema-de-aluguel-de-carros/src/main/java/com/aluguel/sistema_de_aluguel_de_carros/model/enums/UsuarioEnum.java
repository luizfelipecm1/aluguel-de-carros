package com.aluguel.sistema_de_aluguel_de_carros.model.enums;

public enum UsuarioEnum {
    CLIENTE,
    AGENTE;

    @Override
    public String toString() {
        return this.name();
    }
}
