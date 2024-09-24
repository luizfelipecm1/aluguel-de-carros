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
public class Sistema {
    private List<Cliente> clientes;
    private List<Agente> agentes;
    private List<Automovel> automoveis;

    public void gerenciarPedidos() {

    }

    public void gerenciarContratos() {

    }

}
