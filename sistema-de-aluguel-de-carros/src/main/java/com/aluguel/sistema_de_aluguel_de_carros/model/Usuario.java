package com.aluguel.sistema_de_aluguel_de_carros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int idUsuario;
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String profissao;

    public void cadastrarPedidos(){

    }

    public void alterarPedidos() {

    }

    public void consultarPedidos() {

    }

    public void cancelarPedidos() {

    }


}
