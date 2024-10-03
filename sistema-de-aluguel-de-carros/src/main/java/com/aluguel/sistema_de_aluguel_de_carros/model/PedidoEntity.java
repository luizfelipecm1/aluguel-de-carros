package com.aluguel.sistema_de_aluguel_de_carros.model;

import com.aluguel.sistema_de_aluguel_de_carros.model.enums.PedidoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "cliente_entity")
@Getter
@Setter
@AllArgsConstructor

public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Enumerated(EnumType.STRING)
    private PedidoEnum status;

    @ManyToOne
    @JoinColumn(name = "nome")
    private UsuarioEntity cliente;

    @ManyToOne
    @JoinColumn(name = "matricula")
    private AutomovelEntity automovel;

    public PedidoEntity(){

    }

}
