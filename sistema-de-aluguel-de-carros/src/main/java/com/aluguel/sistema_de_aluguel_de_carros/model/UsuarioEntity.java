package com.aluguel.sistema_de_aluguel_de_carros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String profissao;

    public void cadastrarPedidos(){

    }

    public void alterarPedidos(){

    }

    public void consultarPedidos(){

    }

    public void cancelarPedidos(){

    }

}
