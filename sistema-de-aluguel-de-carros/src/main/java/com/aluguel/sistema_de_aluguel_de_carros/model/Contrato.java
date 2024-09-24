package com.aluguel.sistema_de_aluguel_de_carros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {
    private String tipo;
    private List<Automovel> automovelAlugado;
    private List<Agente> agente;

    public void gerarContrato() {

    }
}
