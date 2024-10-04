package com.example.alugueldeveiculos.model;

import com.example.alugueldeveiculos.model.enums.PedidoEnum;
import jakarta.persistence.*;


@Entity
@Table(name = "cliente_entity")
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

    public PedidoEntity(int idPedido, PedidoEnum status, UsuarioEntity cliente, AutomovelEntity automovel) {
        this.idPedido = idPedido;
        this.status = status;
        this.cliente = cliente;
        this.automovel = automovel;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public PedidoEnum getStatus() {
        return status;
    }

    public void setStatus(PedidoEnum status) {
        this.status = status;
    }

    public UsuarioEntity getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioEntity cliente) {
        this.cliente = cliente;
    }

    public AutomovelEntity getAutomovel() {
        return automovel;
    }

    public void setAutomovel(AutomovelEntity automovel) {
        this.automovel = automovel;
    }
}
