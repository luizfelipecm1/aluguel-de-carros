package com.aluguel.sistema_de_aluguel_de_carros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Automovel {
    private String matricula;
    private int ano;
    private String marca;
    private String placa;
    private String modelo;

}
