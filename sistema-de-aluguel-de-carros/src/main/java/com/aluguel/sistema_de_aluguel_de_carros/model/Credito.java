package com.aluguel.sistema_de_aluguel_de_carros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Credito {
    private float valorCredito;
    private Agente banco;

    public void aprovatCredito(){

    }

    public void registarPagamento() {

    }
}
